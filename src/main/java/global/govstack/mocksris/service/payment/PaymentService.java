package global.govstack.mocksris.service.payment;

import global.govstack.mocksris.payment.dto.PaymentOnboardingBeneficiaryDTO;
import global.govstack.mocksris.payment.dto.PaymentResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PaymentService {

    private final HttpHeaders httpHeaders;

    @Value("${payment.bb.im.base-url}")
    private String base_url;

    public PaymentService(@Value("${payment.bb.im.header}") String base_header) {
        httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("X-Road-Client", base_header);
    }

    public String health() {
        return new RestTemplate().exchange(
                base_url + "/actuator/health",
                HttpMethod.GET,
                new HttpEntity<>(null, httpHeaders),
                String.class).getBody();
    }

    public PaymentResponseDTO registerBeneficiary(PaymentOnboardingBeneficiaryDTO data) {
        return new RestTemplate().postForObject(
                base_url + "/register-beneficiary",
                new HttpEntity<>(data, httpHeaders),
                PaymentResponseDTO.class);
    }

    public PaymentResponseDTO updateBeneficiary(PaymentOnboardingBeneficiaryDTO data) {
        return new RestTemplate().postForObject(
                base_url + "/update-beneficiary-details",
                new HttpEntity<>(data, httpHeaders),
                PaymentResponseDTO.class);
    }

}
