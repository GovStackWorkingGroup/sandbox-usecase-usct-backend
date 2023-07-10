package global.govstack.mocksris.service;

import global.govstack.mocksris.configuration.InformationMediatorProperties;
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
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    private final HttpHeaders httpHeaders;
   private final InformationMediatorProperties informationMediatorproperties;
   private final PaymentProperties paymentProperties;

    public PaymentService(InformationMediatorProperties informationMediatorproperties, PaymentProperties paymentProperties) {
        this.informationMediatorproperties = informationMediatorproperties;
        this.paymentProperties = paymentProperties;
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("X-Road-Client", informationMediatorproperties.header());
    }

    public PaymentResponseDTO orderPayment(List<Beneficiary> beneficiaryList) {

        String requestId = UUID.randomUUID().toString();
        String batchId = UUID.randomUUID().toString();
        var creditInstructionsDTO = beneficiaryList.stream().map( beneficiary -> {
            String instructionId = UUID.randomUUID().toString();
            var payeeFunctionalId = beneficiary.getPerson().getFoundationalId() +
                    paymentProperties.governmentIdentifier() +
                    beneficiary.getEnrolledPackage().getId().toString();
            var amount = beneficiary.getEnrolledPackage().getAmount();
            var currency = beneficiary.getEnrolledPackage().getCurrency();
            var narration = "Payment for " + beneficiary.getEnrolledPackage().getName() + " package";
            return  new PaymentCreditInstructionsDTO(instructionId, payeeFunctionalId, amount, currency, narration);
        }).toList();
        var paymentDTO = new PaymentDTO(requestId, paymentProperties.sourceBbId(), batchId, creditInstructionsDTO);
        prevalidatePayment(paymentDTO);
        return bulkPayment(paymentDTO);
    }

    public String health() {
        return new RestTemplate().exchange(
                informationMediatorproperties.baseUrl() + "/actuator/health",
                HttpMethod.GET,
                new HttpEntity<>(null, httpHeaders),
                String.class).getBody();
    }

    public PaymentResponseDTO registerBeneficiary(PaymentOnboardingBeneficiaryDTO data) {
        return new RestTemplate().postForObject(
                informationMediatorproperties.baseUrl() + "/register-beneficiary",
                new HttpEntity<>(data, httpHeaders),
                PaymentResponseDTO.class);
    }

    public PaymentResponseDTO updateBeneficiary(PaymentOnboardingBeneficiaryDTO data) {
        return new RestTemplate().postForObject(
                informationMediatorproperties.baseUrl() + "/update-beneficiary-details",
                new HttpEntity<>(data, httpHeaders),
                PaymentResponseDTO.class);
    }

    public PaymentResponseDTO prevalidatePayment(PaymentDTO data) {
        return new RestTemplate().postForObject(
                informationMediatorproperties.baseUrl() + "/prepayment-validation",
                new HttpEntity<>(data, httpHeaders),
                PaymentResponseDTO.class);
    }

    public PaymentResponseDTO bulkPayment(PaymentDTO data) {
        return new RestTemplate().postForObject(
                informationMediatorproperties.baseUrl() + "/bulk-payment",
                new HttpEntity<>(data, httpHeaders),
                PaymentResponseDTO.class);
    }
}
