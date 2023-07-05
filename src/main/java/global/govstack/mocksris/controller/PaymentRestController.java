package global.govstack.mocksris.controller;

import global.govstack.mocksris.controller.dto.PaymentDTO;
import global.govstack.mocksris.controller.dto.PaymentOnboardingBeneficiaryDTO;
import global.govstack.mocksris.controller.dto.PaymentResponseDTO;
import global.govstack.mocksris.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentRestController {
    private final PaymentService paymentService;

    public PaymentRestController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/emulator-health")
    public String getHealth() {
        return paymentService.health();
    }

    @PostMapping(value = "/order-payment")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponseDTO create(@RequestBody final PaymentDTO paymentDTO) {
       return paymentService.orderPayment(paymentDTO);
    }
}

