package global.govstack.mocksris.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import global.govstack.mocksris.configuration.PaymentHubProperties;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentHubService {

    private final HttpHeaders httpHeaders;
    private final PaymentHubProperties paymentHubProperties;
    private final HttpComponentsClientHttpRequestFactory requestFactory;

    public PaymentHubService(PaymentHubProperties paymentHubProperties, HttpComponentsClientHttpRequestFactory requestFactory) {
        this.paymentHubProperties = paymentHubProperties;
        this.requestFactory = requestFactory;
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    public String health() {
        String acHealth = new RestTemplate().exchange(
                paymentHubProperties.accountMapperURL() + "/actuator/health",
                HttpMethod.GET,
                new HttpEntity<>(null, httpHeaders),
                String.class).getBody();
        String bcHealth = new RestTemplate(requestFactory).exchange(
                paymentHubProperties.bulkConnectorURL() + "/actuator/health",
                HttpMethod.GET,
                new HttpEntity<>(null, httpHeaders),
                String.class).getBody();

        return String.format("{\"account_mapper\": %s, \"bulk_connector\": %s", acHealth, bcHealth);
    }

    /*public PaymentResponseDTO registerBeneficiary(PaymentOnboardingBeneficiaryDTO data) {
        try {
            return new RestTemplate().postForObject(
                    paymentBBInformationMediatorproperties.baseUrl() + "/register-beneficiary",
                    new HttpEntity<>(data, httpHeaders),
                    PaymentResponseDTO.class);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
        }
    }

    public PaymentResponseDTO orderPayment(List<Beneficiary> beneficiaryList) {

        String requestId = UUID.randomUUID().toString();
        String batchId = UUID.randomUUID().toString();
        var creditInstructionsDTO = beneficiaryList.stream().map(beneficiary -> {
            String instructionId = UUID.randomUUID().toString();
            var payeeFunctionalId = beneficiary.getPerson().getPersonalIdCode() +
                    paymentProperties.governmentIdentifier() +
                    beneficiary.getEnrolledPackage().getId().toString();
            var amount = beneficiary.getEnrolledPackage().getAmount();
            var currency = beneficiary.getEnrolledPackage().getCurrency();
            var narration = "Payment for " + beneficiary.getEnrolledPackage().getName() + " package";
            return new PaymentCreditInstructionsDTO(instructionId, payeeFunctionalId, amount, currency, narration);
        }).toList();
        var paymentDTO = new PaymentDTO(requestId, paymentProperties.sourceBbId(), batchId, creditInstructionsDTO);
        return bulkPayment(paymentDTO);
    }

    public PaymentResponseDTO bulkPayment(PaymentDTO data) {
        try {
            return new RestTemplate().postForObject(
                    paymentBBInformationMediatorproperties.baseUrl() + "/bulk-payment",
                    new HttpEntity<>(data, httpHeaders),
                    PaymentResponseDTO.class);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
        }
    }*/
}
