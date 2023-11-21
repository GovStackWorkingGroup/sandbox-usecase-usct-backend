package global.govstack.usct.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import global.govstack.usct.configuration.PaymentBBInformationMediatorProperties;
import global.govstack.usct.configuration.PaymentProperties;
import global.govstack.usct.controller.dto.digital.registries.PackageDto;
import global.govstack.usct.model.Beneficiary;
import global.govstack.usct.model.PaymentDisbursement;
import global.govstack.usct.repositories.BeneficiaryRepository;
import global.govstack.usct.repositories.PaymentDisbursementRepository;
import global.govstack.usct.service.dto.emulator.*;
import global.govstack.usct.types.PaymentOnboardingCallbackMode;
import global.govstack.usct.types.PaymentOnboardingStatus;
import java.util.List;
import java.util.UUID;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@ConditionalOnProperty(name = "payment.config.mode", havingValue = "emulator")
public class PaymentEmulatorService implements PaymentService {

  private final HttpHeaders httpHeaders;
  private final PaymentBBInformationMediatorProperties paymentBBInformationMediatorproperties;
  private final PaymentProperties paymentProperties;
  private final BeneficiaryRepository beneficiaryRepository;
  private final PaymentDisbursementRepository paymentDisbursementRepository;
  private final RestTemplate restTemplate;
  private final PackageService packageService;

  private ObjectMapper objectMapper = new ObjectMapper();

  public PaymentEmulatorService(
      PaymentBBInformationMediatorProperties paymentBBInformationMediatorproperties,
      PaymentProperties paymentProperties,
      BeneficiaryRepository beneficiaryRepository,
      PaymentDisbursementRepository paymentDisbursementRepository,
      PackageService packageService) {
    this.paymentBBInformationMediatorproperties = paymentBBInformationMediatorproperties;
    this.paymentProperties = paymentProperties;
    this.beneficiaryRepository = beneficiaryRepository;
    this.paymentDisbursementRepository = paymentDisbursementRepository;
    this.packageService = packageService;
    httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.add("X-Road-Client", paymentBBInformationMediatorproperties.header());
    this.restTemplate = new RestTemplate();
  }

  @Override
  public void orderPayment(List<Beneficiary> beneficiaries) {
    var beneficiaryList =
        beneficiaryRepository.findAllById(beneficiaries.stream().map(b -> b.getId()).toList());

    validateOnboardingStatus(beneficiaryList);

    String requestId = UUID.randomUUID().toString();
    String batchId = UUID.randomUUID().toString();
    List<PackageDto> packages = packageService.findAll();

    var creditInstructionsDTO =
        beneficiaryList.stream()
            .map(
                beneficiary -> {
                  PackageDto packageDto =
                      packages.stream()
                          .filter(item -> item.getId() == beneficiary.getEnrolledPackageId())
                          .findFirst()
                          .orElse(new PackageDto(1, "default", "default package", 0));
                  String instructionId = UUID.randomUUID().toString();
                  var payeeFunctionalId =
                      beneficiary.getPerson().getPersonalIdCode()
                          + paymentProperties.governmentIdentifier()
                          + packageDto.getId();
                  var amount = packageDto.getAmount();
                  var currency = packageDto.getCurrency();
                  var narration = "Payment for " + packageDto.getName() + " package";
                  return new PaymentCreditInstructionsDTO(
                      instructionId, payeeFunctionalId, amount, currency, narration);
                })
            .toList();
    var paymentDTO =
        new PaymentDTO(requestId, paymentProperties.sourceBbId(), batchId, creditInstructionsDTO);
    prevalidatePayment(paymentDTO);
    var result = bulkPayment(paymentDTO);

    var request = "{\"headers\":%s, \"body\":%s}";
    try {
      request =
          String.format(
              request,
              objectMapper.writeValueAsString(httpHeaders.entrySet()),
              objectMapper.writeValueAsString(paymentDTO));
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

    var response = "";
    try {
      response = objectMapper.writeValueAsString(result);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }

    paymentDisbursementRepository.save(
        new PaymentDisbursement("emulator", requestId, request, response));
  }

  public void updatePaymentOrderStatus(String callbackBody) {}

  @Override
  public void updatePaymentOnboardingStatus(
      String data, PaymentOnboardingCallbackMode onboardingMode) {}

  @Override
  public String health() {
    return restTemplate
        .exchange(
            paymentBBInformationMediatorproperties.baseUrl() + "/actuator/health",
            HttpMethod.GET,
            new HttpEntity<>(null, httpHeaders),
            String.class)
        .getBody();
  }

  @Override
  @Transactional
  public void registerBeneficiary(List<Beneficiary> beneficiaries) {
    var requestID = UUID.randomUUID().toString();
    PaymentOnboardingBeneficiaryDTO paymentDto = convertBeneficiary(beneficiaries, requestID);
    try {
      restTemplate.postForObject(
          paymentBBInformationMediatorproperties.baseUrl() + "/register-beneficiary",
          new HttpEntity<>(paymentDto, httpHeaders),
          PaymentResponseDTO.class);
      updateBeneficiaryPaymentStatus(beneficiaries, PaymentOnboardingStatus.ONBOARDED, requestID);
    } catch (HttpClientErrorException ex) {
      if (ex.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
        updateBeneficiary(beneficiaries);
      } else {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
      }
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
    }
  }

  @Override
  @Transactional
  public void updateBeneficiary(List<Beneficiary> beneficiaries) {
    var requestID = UUID.randomUUID().toString();
    PaymentOnboardingBeneficiaryDTO paymentDto = convertBeneficiary(beneficiaries, requestID);
    try {
      restTemplate.postForObject(
          paymentBBInformationMediatorproperties.baseUrl() + "/update-beneficiary-details",
          new HttpEntity<>(paymentDto, httpHeaders),
          PaymentResponseDTO.class);
      updateBeneficiaryPaymentStatus(beneficiaries, PaymentOnboardingStatus.ONBOARDED, requestID);
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
    }
  }

  private List<Beneficiary> updateBeneficiaryPaymentStatus(
      List<Beneficiary> beneficiaries, PaymentOnboardingStatus status, String requestID) {
    beneficiaries.forEach(
        beneficiary -> {
          beneficiary.setPaymentOnboardingStatus(status);
          beneficiary.setPaymentOnboardingRequestId(requestID);
        });
    return beneficiaryRepository.saveAll(beneficiaries);
  }

  private PaymentOnboardingBeneficiaryDTO convertBeneficiary(
      List<Beneficiary> beneficiaries, String requestID) {
    var beneficiaryDTO =
        beneficiaries.stream()
            .map(
                beneficiary ->
                    new PaymentOnboardingBeneficiaryDetailsDTO(
                        beneficiary.getFunctionalId(),
                        beneficiary.getPerson().getFinancialModality().getCode(),
                        beneficiary.getPerson().getFinancialAddress()))
            .toList();

    var paymentDto =
        new PaymentOnboardingBeneficiaryDTO(
            requestID, paymentProperties.sourceBbId(), beneficiaryDTO);
    return paymentDto;
  }

  private PaymentResponseDTO prevalidatePayment(PaymentDTO data) {
    try {
      return restTemplate.postForObject(
          paymentBBInformationMediatorproperties.baseUrl() + "/prepayment-validation",
          new HttpEntity<>(data, httpHeaders),
          PaymentResponseDTO.class);
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
    }
  }

  private PaymentResponseDTO bulkPayment(PaymentDTO data) {
    try {
      return restTemplate.postForObject(
          paymentBBInformationMediatorproperties.baseUrl() + "/bulk-payment",
          new HttpEntity<>(data, httpHeaders),
          PaymentResponseDTO.class);
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
    }
  }
}
