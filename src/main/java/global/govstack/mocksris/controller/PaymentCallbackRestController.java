package global.govstack.mocksris.controller;

import global.govstack.mocksris.service.PaymentService;
import global.govstack.mocksris.types.PaymentOnboardingCallbackMode;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/callback/payment")
@CrossOrigin
@PreAuthorize("hasRole('PAYMENT_OFFICER')")
public class PaymentCallbackRestController {
  private final PaymentService paymentService;

  public PaymentCallbackRestController(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  @PreAuthorize("permitAll()")
  @PutMapping(value = "/beneficiary-register")
  @ResponseStatus(HttpStatus.OK)
  public void beneficiaryRegisterOnboardingCB(@RequestBody String body) throws IOException {
    paymentService.updatePaymentOnboardingStatus(body, PaymentOnboardingCallbackMode.REGISTER);
  }

  @PreAuthorize("permitAll()")
  @PutMapping(value = "/beneficiary-update")
  @ResponseStatus(HttpStatus.OK)
  public void beneficiaryUpdateOnboardingCB(@RequestBody String body) throws IOException {
    paymentService.updatePaymentOnboardingStatus(body, PaymentOnboardingCallbackMode.UPDATE);
  }

  @PreAuthorize("permitAll()")
  @PutMapping(value = "/payment")
  @ResponseStatus(HttpStatus.OK)
  public void beneficiaryPaymentCB(@RequestBody String body) throws IOException {
    paymentService.updatePaymentOrderStatus(body);
  }
}
