package global.govstack.mocksris.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import global.govstack.mocksris.configuration.PaymentBBInformationMediatorProperties;
import global.govstack.mocksris.configuration.PaymentProperties;
import global.govstack.mocksris.controller.dto.*;
import global.govstack.mocksris.model.Beneficiary;
import global.govstack.mocksris.model.PaymentDisbursement;
import global.govstack.mocksris.repositories.BeneficiaryRepository;
import global.govstack.mocksris.repositories.PaymentDisbursementRepository;
import global.govstack.mocksris.types.PaymentOnboardingCallbackMode;
import global.govstack.mocksris.types.PaymentOnboardingStatus;
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

  private ObjectMapper objectMapper = new ObjectMapper();

  public PaymentEmulatorService(
      PaymentBBInformationMediatorProperties paymentBBInformationMediatorproperties,
      PaymentProperties paymentProperties,
      BeneficiaryRepository beneficiaryRepository,
      PaymentDisbursementRepository paymentDisbursementRepository) {
    this.paymentBBInformationMediatorproperties = paymentBBInformationMediatorproperties;
    this.paymentProperties = paymentProperties;
    this.beneficiaryRepository = beneficiaryRepository;
    this.paymentDisbursementRepository = paymentDisbursementRepository;
    httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.add("X-Road-Client", paymentBBInformationMediatorproperties.header());
  }

  @Override
  public void orderPayment(List<Beneficiary> beneficiaries) {
    var beneficiaryList =
        beneficiaryRepository.findAllById(beneficiaries.stream().map(b -> b.getId()).toList());

    validateOnboardingStatus(beneficiaryList);

    String requestId = UUID.randomUUID().toString();
    String batchId = UUID.randomUUID().toString();
    var creditInstructionsDTO =
        beneficiaryList.stream()
            .map(
                beneficiary -> {
                  String instructionId = UUID.randomUUID().toString();
                  var payeeFunctionalId =
                      beneficiary.getPerson().getPersonalIdCode()
                          + paymentProperties.governmentIdentifier()
                          + beneficiary.getEnrolledPackage().getId().toString();
                  var amount = beneficiary.getEnrolledPackage().getAmount();
                  var currency = beneficiary.getEnrolledPackage().getCurrency();
                  var narration =
                      "Payment for " + beneficiary.getEnrolledPackage().getName() + " package";
                  return new PaymentCreditInstructionsDTO(
                      instructionId, payeeFunctionalId, amount, currency, narration);
                })
            .toList();
    var paymentDTO =
        new PaymentDTO(requestId, paymentProperties.sourceBbId(), batchId, creditInstructionsDTO);
    prevalidatePayment(paymentDTO);
    var result = bulkPayment(paymentDTO);

    var request = "\"headers\":%s, \"body\":%s";
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
      List<String> failedFunctionalIds,
      String requestId,
      PaymentOnboardingCallbackMode onboardingMode) {}

  @Override
  public String health() {
    return new RestTemplate()
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
      new RestTemplate()
          .postForObject(
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
      new RestTemplate()
          .postForObject(
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
      return new RestTemplate()
          .postForObject(
              paymentBBInformationMediatorproperties.baseUrl() + "/prepayment-validation",
              new HttpEntity<>(data, httpHeaders),
              PaymentResponseDTO.class);
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
    }
  }

  private PaymentResponseDTO bulkPayment(PaymentDTO data) {
    try {
      return new RestTemplate()
          .postForObject(
              paymentBBInformationMediatorproperties.baseUrl() + "/bulk-payment",
              new HttpEntity<>(data, httpHeaders),
              PaymentResponseDTO.class);
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
    }
  }

  @Override
  public List<PaymentDisbursement> getPaymentDisbursements() {
    return paymentDisbursementRepository.findAll();
  }
}
