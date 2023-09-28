package global.govstack.mocksris.service;

import global.govstack.mocksris.configuration.PaymentHubProperties;
import global.govstack.mocksris.controller.dto.PaymentHubBeneficiaryResponseDTO;
import global.govstack.mocksris.controller.dto.PaymentHubOnboardingBeneficiaryDTO;
import global.govstack.mocksris.controller.dto.PaymentHubOnboardingBeneficiaryDetailsDTO;
import global.govstack.mocksris.model.Beneficiary;
import global.govstack.mocksris.repositories.BeneficiaryRepository;
import global.govstack.mocksris.types.PaymentOnboardingCallbackMode;
import global.govstack.mocksris.types.PaymentOnboardingStatus;
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

  public PaymentHubService(
      PaymentHubProperties paymentHubProperties,
      HttpComponentsClientHttpRequestFactory requestFactory,
      BeneficiaryRepository beneficiaryRepository) {
    this.paymentHubProperties = paymentHubProperties;
    this.requestFactory = requestFactory;
    this.beneficiaryRepository = beneficiaryRepository;
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
    // TODO: REPLACE WITH CONFIG CB url
    // httpHeaders.add("X-CallbackURL", paymentHubProperties.callbackBaseUrl() +
    // "/api/v1/beneficiary-callback");
    httpHeaders.add("X-CallbackURL", "https://webhook.site/cf437b0e-06a3-4dbc-b994-10b61daf5704");
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
    // TODO: REPLACE WITH CONFIG CB url
    // httpHeaders.add("X-CallbackURL", paymentHubProperties.callbackBaseUrl() +
    // "/api/v1/beneficiary-callback");
    httpHeaders.add("X-CallbackURL", "https://webhook.site/cf437b0e-06a3-4dbc-b994-10b61daf5704");
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

    if (onboardingMode.equals(PaymentOnboardingCallbackMode.REGISTER)) {
      updateBeneficiary(failedBeneficiaries);
    }
  }

  @Override
  public void orderPayment(List<Beneficiary> beneficiaryList) {}
}
