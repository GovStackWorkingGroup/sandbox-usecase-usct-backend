package global.govstack.mocksris.controller;

import global.govstack.mocksris.controller.dto.BeneficiaryDto;
import global.govstack.mocksris.controller.dto.PaymentHubBeneficairyOnboardingCallbackDTO;
import global.govstack.mocksris.controller.dto.PaymentHubBeneficairyOnboardingFailedCallbackDTO;
import global.govstack.mocksris.model.Beneficiary;
import global.govstack.mocksris.service.PaymentService;
import global.govstack.mocksris.types.PaymentOnboardingCallbackMode;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@CrossOrigin
public class PaymentRestController {
  private final PaymentService paymentService;
  private final ModelMapper modelMapper;

  public PaymentRestController(PaymentService paymentService, ModelMapper modelMapper) {
    this.paymentService = paymentService;
    this.modelMapper = modelMapper;
  }

  @GetMapping("/health")
  public String getHealth() {
    return paymentService.health();
  }

  @PostMapping(value = "/order-payment")
  @ResponseStatus(HttpStatus.CREATED)
  public String create(@RequestBody final List<BeneficiaryDto> beneficiaryDtos) {
    List<Beneficiary> list = beneficiaryDtos.stream().map(this::convertToEntity).toList();
    paymentService.orderPayment(list);
    return "Thank you! Payment order received!";
  }

  @PutMapping(value = "/beneficiary-register-callback")
  @ResponseStatus(HttpStatus.OK)
  public void beneficiaryRegisterOnboardingCB(
      @RequestBody final PaymentHubBeneficairyOnboardingCallbackDTO beneficiaryCBDto) {
    beneficiaryOnboardingCB(beneficiaryCBDto, PaymentOnboardingCallbackMode.REGISTER);
  }

  @PutMapping(value = "/beneficiary-update-callback")
  @ResponseStatus(HttpStatus.OK)
  public void beneficiaryUpdateOnboardingCB(
      @RequestBody final PaymentHubBeneficairyOnboardingCallbackDTO beneficiaryCBDto) {
    beneficiaryOnboardingCB(beneficiaryCBDto, PaymentOnboardingCallbackMode.UPDATE);
  }

  private void beneficiaryOnboardingCB(
      final PaymentHubBeneficairyOnboardingCallbackDTO beneficiaryCBDto,
      PaymentOnboardingCallbackMode mode) {
    var failedBeneficiaries =
        beneficiaryCBDto.failedCases().stream()
            .map(PaymentHubBeneficairyOnboardingFailedCallbackDTO::payeeIdentity)
            .toList();
    paymentService.updatePaymentOnboardingStatus(
        failedBeneficiaries, beneficiaryCBDto.requestID(), mode);
  }

  private Beneficiary convertToEntity(BeneficiaryDto beneficiaryDto) {
    return modelMapper.map(beneficiaryDto, Beneficiary.class);
  }
}
