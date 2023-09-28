package global.govstack.mocksris.service;

import global.govstack.mocksris.configuration.PaymentBBInformationMediatorProperties;
import global.govstack.mocksris.configuration.PaymentProperties;
import global.govstack.mocksris.controller.dto.*;
import global.govstack.mocksris.model.Beneficiary;
import global.govstack.mocksris.types.PaymentOnboardingCallbackMode;
import java.util.List;
import java.util.UUID;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@ConditionalOnProperty(name = "payment.config.mode", havingValue = "emulator")
public class PaymentEmulatorService implements PaymentService {

  private final HttpHeaders httpHeaders;
  private final PaymentBBInformationMediatorProperties paymentBBInformationMediatorproperties;
  private final PaymentProperties paymentProperties;

  public PaymentEmulatorService(
      PaymentBBInformationMediatorProperties paymentBBInformationMediatorproperties,
      PaymentProperties paymentProperties) {
    this.paymentBBInformationMediatorproperties = paymentBBInformationMediatorproperties;
    this.paymentProperties = paymentProperties;
    httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.add("X-Road-Client", paymentBBInformationMediatorproperties.header());
  }

  @Override
  public void orderPayment(List<Beneficiary> beneficiaryList) {
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
    bulkPayment(paymentDTO);
  }

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
  public void registerBeneficiary(List<Beneficiary> beneficiaries) {
    var requestID = UUID.randomUUID().toString();
    PaymentOnboardingBeneficiaryDTO paymentDto = convertBeneficiary(beneficiaries, requestID);
    try {
      new RestTemplate()
          .postForObject(
              paymentBBInformationMediatorproperties.baseUrl() + "/register-beneficiary",
              new HttpEntity<>(paymentDto, httpHeaders),
              PaymentResponseDTO.class);
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
    }
  }

  @Override
  public void updateBeneficiary(List<Beneficiary> beneficiaries) {
    var requestID = UUID.randomUUID().toString();
    PaymentOnboardingBeneficiaryDTO paymentDto = convertBeneficiary(beneficiaries, requestID);
    try {
      new RestTemplate()
          .postForObject(
              paymentBBInformationMediatorproperties.baseUrl() + "/update-beneficiary-details",
              new HttpEntity<>(paymentDto, httpHeaders),
              PaymentResponseDTO.class);
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
    }
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
}
