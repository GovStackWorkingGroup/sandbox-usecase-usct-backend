package global.govstack.mocksris.controller;

import global.govstack.mocksris.controller.dto.BeneficiaryDto;
import global.govstack.mocksris.controller.dto.PaymentDisbursementDto;
import global.govstack.mocksris.model.Beneficiary;
import global.govstack.mocksris.service.PaymentDisbursementService;
import global.govstack.mocksris.service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@CrossOrigin
@PreAuthorize("hasRole('PAYMENT_OFFICER')")
public class PaymentRestController {
  private final PaymentService paymentService;
  private final PaymentDisbursementService paymentDisbursementService;
  private final ModelMapper modelMapper;

  public PaymentRestController(
      PaymentService paymentService,
      PaymentDisbursementService paymentDisbursementService,
      ModelMapper modelMapper) {
    this.paymentService = paymentService;
    this.paymentDisbursementService = paymentDisbursementService;
    this.modelMapper = modelMapper;
  }

  @PreAuthorize("permitAll()")
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

  @GetMapping(value = "/payment-orders")
  @ResponseStatus(HttpStatus.OK)
  public List<PaymentDisbursementDto> getPaymentOrders(HttpServletRequest request)
      throws IOException {
    var paymentDisbursementsDto =
        paymentDisbursementService.getPaymentDisbursements().stream()
            .map(PaymentDisbursementDto::new)
            .toList();
    return paymentDisbursementsDto;
  }

  private Beneficiary convertToEntity(BeneficiaryDto beneficiaryDto) {
    return modelMapper.map(beneficiaryDto, Beneficiary.class);
  }
}
