package global.govstack.mocksris.web.controller;

import global.govstack.mocksris.model.BeneficiaryResponse;
import global.govstack.mocksris.service.PaymentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PaymentRestController {

    private final PaymentService paymentService;

    public PaymentRestController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @RequestMapping(value = "/payments",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public BeneficiaryResponse createPayment() {
        return paymentService.createPayment();
    }

}
