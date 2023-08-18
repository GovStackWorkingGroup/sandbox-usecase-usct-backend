package global.govstack.mocksris.service;

import global.govstack.mocksris.configuration.PaymentBBInformationMediatorProperties;
import global.govstack.mocksris.configuration.PaymentProperties;
import global.govstack.mocksris.controller.dto.PaymentCreditInstructionsDTO;
import global.govstack.mocksris.controller.dto.PaymentDTO;
import global.govstack.mocksris.controller.dto.PaymentOnboardingBeneficiaryDTO;
import global.govstack.mocksris.controller.dto.PaymentResponseDTO;
import global.govstack.mocksris.model.Beneficiary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    private final HttpHeaders httpHeaders;
    private final PaymentBBInformationMediatorProperties paymentBBInformationMediatorproperties;
    private final PaymentProperties paymentProperties;

    public PaymentService(PaymentBBInformationMediatorProperties paymentBBInformationMediatorproperties, PaymentProperties paymentProperties) {
        this.paymentBBInformationMediatorproperties = paymentBBInformationMediatorproperties;
        this.paymentProperties = paymentProperties;
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("X-Road-Client", paymentBBInformationMediatorproperties.header());
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
        prevalidatePayment(paymentDTO);
        return bulkPayment(paymentDTO);
    }

    public String health() {
        return new RestTemplate().exchange(
                paymentBBInformationMediatorproperties.baseUrl() + "/actuator/health",
                HttpMethod.GET,
                new HttpEntity<>(null, httpHeaders),
                String.class).getBody();
    }

    public PaymentResponseDTO registerBeneficiary(PaymentOnboardingBeneficiaryDTO data) {
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

    public PaymentResponseDTO updateBeneficiary(PaymentOnboardingBeneficiaryDTO data) {
        return new RestTemplate().postForObject(
                paymentBBInformationMediatorproperties.baseUrl() + "/update-beneficiary-details",
                new HttpEntity<>(data, httpHeaders),
                PaymentResponseDTO.class);
    }

    public PaymentResponseDTO prevalidatePayment(PaymentDTO data) {
        try {
            return new RestTemplate().postForObject(
                    paymentBBInformationMediatorproperties.baseUrl() + "/prepayment-validation",
                    new HttpEntity<>(data, httpHeaders),
                    PaymentResponseDTO.class);
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
        }
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
    }
}
