package global.govstack.mocksris.payment;

import global.govstack.mocksris.payment.dto.PaymentDTO;
import global.govstack.mocksris.payment.dto.PaymentOnboardingBeneficiaryDTO;
import global.govstack.mocksris.payment.dto.PaymentResponseDTO;
import global.govstack.mocksris.service.payment.PaymentService;
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

    @ApiOperation(value = "Register a beneficiary", nickname = "registerBeneficiary", notes = "", response = PaymentResponseDTO.class, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful response", response = PaymentResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = PaymentResponseDTO.class) })
    @RequestMapping(value = "/register-beneficiary",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    public PaymentResponseDTO registerBeneficiary(@RequestBody final PaymentOnboardingBeneficiaryDTO requestBody) {
        return paymentService.registerBeneficiary(requestBody);
    }

    @ApiOperation(value = "Update a beneficiary", nickname = "updateBeneficiary", notes = "", response = PaymentResponseDTO.class, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful response", response = PaymentResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = PaymentResponseDTO.class) })
    @RequestMapping(value = "/update-beneficiary",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    public PaymentResponseDTO updateBeneficiary(@RequestBody final PaymentOnboardingBeneficiaryDTO requestBody) {
        return paymentService.updateBeneficiary(requestBody);
    }

    @ApiOperation(value = "Pre-payment validation", nickname = "prepaymentValidationPost", notes = "", response = PaymentResponseDTO.class, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful response", response = PaymentResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = PaymentResponseDTO.class) })
    @RequestMapping(value = "/prepayment-validation",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    public PaymentResponseDTO prevalidatePayment(@RequestBody final PaymentDTO requestBody) {
        return paymentService.prevalidatePayment(requestBody);
    }

    @ApiOperation(value = "Bulk payment processing", nickname = "bulkPaymentPost", notes = "", response = PaymentResponseDTO.class, tags={  })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful response", response = PaymentResponseDTO.class),
            @ApiResponse(code = 400, message = "Bad request", response = PaymentResponseDTO.class) })
    @RequestMapping(value = "/bulk-payment",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    public PaymentResponseDTO bulkPayment(@RequestBody final PaymentDTO requestBody) {
        return paymentService.bulkPayment(requestBody);
    }

    @ExceptionHandler({HttpClientErrorException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<Object> handle(HttpClientErrorException e) {
        if(e.getStatusCode().isSameCodeAs(HttpStatus.BAD_REQUEST)) {
            return ResponseEntity.badRequest().body(e.getResponseBodyAsString());
        } else {
            throw e;
        }
    }

}

