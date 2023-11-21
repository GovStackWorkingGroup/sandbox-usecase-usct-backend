package global.govstack.usct.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import global.govstack.usct.configuration.PaymentHubBBInformationMediatorProperties;
import global.govstack.usct.configuration.PaymentHubProperties;
import global.govstack.usct.controller.dto.digital.registries.PackageDto;
import global.govstack.usct.model.Beneficiary;
import global.govstack.usct.model.PaymentDisbursement;
import global.govstack.usct.repositories.BeneficiaryRepository;
import global.govstack.usct.repositories.PaymentDisbursementRepository;
import global.govstack.usct.service.dto.paymenthub.*;
import global.govstack.usct.types.PaymentOnboardingCallbackMode;
import global.govstack.usct.types.PaymentOnboardingStatus;
import global.govstack.usct.util.RSAUtil;
import global.govstack.usct.util.SHAUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@ConditionalOnProperty(name = "payment.config.mode", havingValue = "paymenthub")
public class PaymentHubService implements PaymentService {
  private final PaymentHubProperties paymentHubProperties;
  private final PaymentHubBBInformationMediatorProperties paymentHubBBInformationMediatorProperties;
  private final HttpComponentsClientHttpRequestFactory requestFactory;
  private final BeneficiaryRepository beneficiaryRepository;
  private final PaymentDisbursementRepository paymentDisbursementRepository;
  private final RestTemplate restTemplate;
  private final RestTemplate restTemplateSelfSigned;
  private final PackageService packageService;

  private ObjectMapper objectMapper = new ObjectMapper();

  public PaymentHubService(
      PaymentHubProperties paymentHubProperties,
      PaymentHubBBInformationMediatorProperties paymentHubBBInformationMediatorProperties,
      HttpComponentsClientHttpRequestFactory requestFactory,
      BeneficiaryRepository beneficiaryRepository,
      PaymentDisbursementRepository paymentDisbursementRepository,
      PackageService packageService) {
    this.paymentHubProperties = paymentHubProperties;
    this.paymentHubBBInformationMediatorProperties = paymentHubBBInformationMediatorProperties;
    this.requestFactory = requestFactory;
    this.beneficiaryRepository = beneficiaryRepository;
    this.paymentDisbursementRepository = paymentDisbursementRepository;
    this.packageService = packageService;
    this.restTemplate = new RestTemplate();
    this.restTemplateSelfSigned = new RestTemplate(requestFactory);
  }

  @Override
  public String health() {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("X-Road-Client", paymentHubBBInformationMediatorProperties.header());
    String acHealth =
        restTemplate
            .exchange(
                paymentHubProperties.accountMapperURL() + "/actuator/health",
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                String.class)
            .getBody();
    String bcHealth =
        restTemplateSelfSigned
            .exchange(
                paymentHubProperties.bulkConnectorURL() + "/actuator/health",
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                String.class)
            .getBody();

    return String.format("{\"account_mapper\": %s, \"bulk_connector\": %s", acHealth, bcHealth);
  }

  @Override
  public void registerBeneficiary(List<Beneficiary> beneficiaries) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(
        "X-CallbackURL",
        paymentHubProperties.callbackBaseUrl() + "/api/v1/payment/beneficiary-register-callback");
    httpHeaders.add(
        "X-Registering-Institution-ID", paymentHubProperties.registeringInstitutionId());
    httpHeaders.add("X-Road-Client", paymentHubBBInformationMediatorProperties.header());

    var requestID = UUID.randomUUID().toString();
    var paymentHubDto = convertBeneficiaries(beneficiaries, requestID);

    beneficiaries.forEach(
        beneficiary -> {
          beneficiary.setPaymentOnboardingStatus(PaymentOnboardingStatus.REGISTERING);
          beneficiary.setPaymentOnboardingRequestId(requestID);
        });
    beneficiaryRepository.saveAll(beneficiaries);

    try {
      restTemplate.postForObject(
          paymentHubProperties.accountMapperURL() + "/beneficiary",
          new HttpEntity<>(paymentHubDto, httpHeaders),
          PaymentHubBeneficiaryResponseDTO.class);
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
    }
  }

  @Override
  public void updateBeneficiary(List<Beneficiary> beneficiaries) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(
        "X-CallbackURL",
        paymentHubProperties.callbackBaseUrl() + "/api/v1/payment/beneficiary-update-callback");
    httpHeaders.add(
        "X-Registering-Institution-ID", paymentHubProperties.registeringInstitutionId());
    httpHeaders.add("X-Road-Client", paymentHubBBInformationMediatorProperties.header());

    var requestID = UUID.randomUUID().toString();
    var paymentHubDto = convertBeneficiaries(beneficiaries, requestID);

    beneficiaries.forEach(
        beneficiary -> {
          beneficiary.setPaymentOnboardingStatus(PaymentOnboardingStatus.UPDATING);
          beneficiary.setPaymentOnboardingRequestId(requestID);
        });
    beneficiaryRepository.saveAll(beneficiaries);

    try {
      restTemplate.exchange(
          paymentHubProperties.accountMapperURL() + "/beneficiary",
          HttpMethod.PUT,
          new HttpEntity<>(paymentHubDto, httpHeaders),
          PaymentHubBeneficiaryResponseDTO.class);
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
    }
  }

  private PaymentHubOnboardingBeneficiaryDTO convertBeneficiaries(
      List<Beneficiary> beneficiaries, String requestID) {
    var paymentHubDetailsDto =
        beneficiaries.stream()
            .map(
                beneficiary ->
                    new PaymentHubOnboardingBeneficiaryDetailsDTO(
                        beneficiary.getFunctionalId(),
                        beneficiary.getPerson().getFinancialModality().getCode(),
                        beneficiary.getPerson().getFinancialAddress(),
                        beneficiary.getPerson().getIban()))
            .toList();

    return new PaymentHubOnboardingBeneficiaryDTO(requestID, paymentHubDetailsDto);
  }

  @Override
  @Transactional
  public void updatePaymentOnboardingStatus(
      String data, PaymentOnboardingCallbackMode onboardingMode) throws JsonProcessingException {

    var cbBody = objectMapper.readValue(data, PaymentHubBeneficiaryOnboardingCallbackDTO.class);

    var failedFunctionalIds =
        cbBody.failedCases().stream()
            .map(PaymentHubBeneficiaryOnboardingFailedCallbackDTO::payeeIdentity)
            .toList();

    List<Beneficiary> failedBeneficiaries = new ArrayList<>();
    var beneficiaries =
        beneficiaryRepository.findByPaymentOnboardingRequestId(cbBody.registerRequestID());

    beneficiaries.forEach(
        beneficiary -> {
          if (failedFunctionalIds.contains(beneficiary.getFunctionalId())) {
            beneficiary.setPaymentOnboardingStatus(PaymentOnboardingStatus.NONE);
            failedBeneficiaries.add(beneficiary);
          } else {
            beneficiary.setPaymentOnboardingStatus(PaymentOnboardingStatus.ONBOARDED);
          }
          beneficiaryRepository.save(beneficiary);
        });

    beneficiaryRepository.saveAll(beneficiaries);

    if (onboardingMode.equals(PaymentOnboardingCallbackMode.REGISTER)
        && !failedBeneficiaries.isEmpty()) {
      updateBeneficiary(failedBeneficiaries);
    }
  }

  @Override
  @Transactional
  public void orderPayment(List<Beneficiary> beneficiaries) {
    var beneficiaryList =
        beneficiaryRepository.findAllById(beneficiaries.stream().map(b -> b.getId()).toList());

    validateOnboardingStatus(beneficiaryList);

    var requestID = UUID.randomUUID().toString();
    var body = constructOrderPaymentRequestBody(beneficiaryList);

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(
        "X-CallbackURL",
        paymentHubProperties.callbackBaseUrl() + "/api/v1/payment/payment-callback");
    httpHeaders.add(
        "X-Registering-Institution-ID", paymentHubProperties.registeringInstitutionId());
    httpHeaders.add("Purpose", "USCT Payment");
    httpHeaders.add("X-CorrelationID", requestID);
    httpHeaders.add("Platform-TenantId", paymentHubProperties.tenant());
    httpHeaders.add("X-Program-ID", paymentHubProperties.programId());
    httpHeaders.add("X-Signature", signOrderPaymentRequest(requestID, body));
    httpHeaders.add("type", "raw");
    httpHeaders.add("X-Road-Client", paymentHubBBInformationMediatorProperties.header());

    var response =
        restTemplateSelfSigned
            .exchange(
                paymentHubProperties.bulkConnectorURL() + "/batchtransactions?type=raw",
                HttpMethod.POST,
                new HttpEntity<>(body, httpHeaders),
                String.class)
            .getBody();

    var request = "{\"headers\":%s, \"body\":%s}";
    try {
      var headers = objectMapper.writeValueAsString(httpHeaders.entrySet());
      request = String.format(request, headers, body);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

    paymentDisbursementRepository.save(
        new PaymentDisbursement("paymenthub", requestID, request, response));
  }

  @Override
  @Transactional
  public void updatePaymentOrderStatus(String callbackBody) throws JsonProcessingException {
    JsonNode node = objectMapper.readTree(callbackBody);
    var paymentDisbursement =
        paymentDisbursementRepository.findByRequestUUID(node.get("requestId").asText());
    paymentDisbursementRepository.save(paymentDisbursement.setCallback(callbackBody));
  }

  private String constructOrderPaymentRequestBody(List<Beneficiary> beneficiaryList) {
    List<PackageDto> packages = packageService.findAll();
    var rb =
        beneficiaryList.stream()
            .map(
                beneficiary -> {
                  PackageDto packageDto =
                      packages.stream()
                          .filter(item -> item.getId() == beneficiary.getEnrolledPackageId())
                          .findFirst()
                          .orElse(new PackageDto(1, "default", "default package", 0));
                  return new PaymentHubOrderPaymentDTO(
                      UUID.randomUUID().toString(),
                      List.of(
                          new PaymentHubOrderPaymentPartyDTO(
                              "functionalId", beneficiary.getFunctionalId())),
                      paymentHubProperties.paymentMode(),
                      packageDto.getAmount(),
                      packageDto.getCurrency(),
                      "Payment for " + packageDto.getName() + " package");
                })
            .toList();

    try {
      return objectMapper.writeValueAsString(rb);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

  private String signOrderPaymentRequest(String requestID, String body) {
    var sha3 =
        SHAUtils.generateSHA(
            String.format("%s:%s:%s", requestID, paymentHubProperties.tenant(), body), "SHA3-256");
    try {
      return RSAUtil.encrypt(
          sha3, RSAUtil.getPrivateKey(paymentHubProperties.jwsTenantPrivateKey()));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
