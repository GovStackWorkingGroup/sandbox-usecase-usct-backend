package global.govstack.mocksris.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import global.govstack.mocksris.configuration.PaymentHubProperties;
import global.govstack.mocksris.controller.dto.*;
import global.govstack.mocksris.model.Beneficiary;
import global.govstack.mocksris.model.PaymentDisbursement;
import global.govstack.mocksris.repositories.BeneficiaryRepository;
import global.govstack.mocksris.repositories.PaymentDisbursementRepository;
import global.govstack.mocksris.types.PaymentOnboardingCallbackMode;
import global.govstack.mocksris.types.PaymentOnboardingStatus;
import global.govstack.mocksris.util.RSAUtil;
import global.govstack.mocksris.util.SHAUtils;
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
  private final HttpComponentsClientHttpRequestFactory requestFactory;
  private final BeneficiaryRepository beneficiaryRepository;
  private final PaymentDisbursementRepository paymentDisbursementRepository;

  private ObjectMapper objectMapper = new ObjectMapper();

  public PaymentHubService(
      PaymentHubProperties paymentHubProperties,
      HttpComponentsClientHttpRequestFactory requestFactory,
      BeneficiaryRepository beneficiaryRepository,
      PaymentDisbursementRepository paymentDisbursementRepository) {
    this.paymentHubProperties = paymentHubProperties;
    this.requestFactory = requestFactory;
    this.beneficiaryRepository = beneficiaryRepository;
    this.paymentDisbursementRepository = paymentDisbursementRepository;
  }

  @Override
  public String health() {
    String acHealth =
        new RestTemplate()
            .exchange(
                paymentHubProperties.accountMapperURL() + "/actuator/health",
                HttpMethod.GET,
                new HttpEntity<>(null),
                String.class)
            .getBody();
    String bcHealth =
        new RestTemplate(requestFactory)
            .exchange(
                paymentHubProperties.bulkConnectorURL() + "/actuator/health",
                HttpMethod.GET,
                new HttpEntity<>(null),
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

    var requestID = UUID.randomUUID().toString();
    var paymentHubDto = convertBeneficiaries(beneficiaries, requestID);

    beneficiaries.forEach(
        beneficiary -> {
          beneficiary.setPaymentOnboardingStatus(PaymentOnboardingStatus.REGISTERING);
          beneficiary.setPaymentOnboardingRequestId(requestID);
        });
    beneficiaryRepository.saveAll(beneficiaries);

    try {
      new RestTemplate()
          .postForObject(
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

    var requestID = UUID.randomUUID().toString();
    var paymentHubDto = convertBeneficiaries(beneficiaries, requestID);

    beneficiaries.forEach(
        beneficiary -> {
          beneficiary.setPaymentOnboardingStatus(PaymentOnboardingStatus.UPDATING);
          beneficiary.setPaymentOnboardingRequestId(requestID);
        });
    beneficiaryRepository.saveAll(beneficiaries);

    try {
      new RestTemplate()
          .exchange(
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
                        beneficiary.getPerson().getFinancialAddress()))
            .toList();

    return new PaymentHubOnboardingBeneficiaryDTO(requestID, paymentHubDetailsDto);
  }

  @Override
  @Transactional
  public void updatePaymentOnboardingStatus(
      List<String> failedFunctionalIds,
      String requestId,
      PaymentOnboardingCallbackMode onboardingMode) {
    List<Beneficiary> failedBeneficiaries = new ArrayList<>();
    var beneficiaries = beneficiaryRepository.findByPaymentOnboardingRequestId(requestId);

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

    var response =
        new RestTemplate(requestFactory)
            .exchange(
                paymentHubProperties.bulkConnectorURL() + "/batchtransactions?type=raw",
                HttpMethod.POST,
                new HttpEntity<>(body, httpHeaders),
                String.class)
            .getBody();

    var request = "\"headers\":%s, \"body\":%s";
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

  @Override
  public List<PaymentDisbursement> getPaymentDisbursements() {
    return paymentDisbursementRepository.findAll();
  }

  private String constructOrderPaymentRequestBody(List<Beneficiary> beneficiaryList) {
    var rb =
        beneficiaryList.stream()
            .map(
                beneficiary -> {
                  return new PaymentHubOrderPaymentDTO(
                      UUID.randomUUID().toString(),
                      List.of(
                          new PaymentHubOrderPaymentPartyDTO(
                              beneficiary.getPerson().getFinancialModality().getKey(),
                              beneficiary.getFunctionalId())),
                      beneficiary.getPerson().getFinancialModality().paymentMode(),
                      beneficiary.getEnrolledPackage().getAmount(),
                      beneficiary.getEnrolledPackage().getCurrency(),
                      "Payment for " + beneficiary.getEnrolledPackage().getName() + " package");
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
