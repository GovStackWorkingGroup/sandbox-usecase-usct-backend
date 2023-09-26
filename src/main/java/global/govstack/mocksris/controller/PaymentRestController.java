package global.govstack.mocksris.controller;

import java.util.List;
import java.util.Objects;

import global.govstack.mocksris.configuration.PaymentConfigProperties;
import global.govstack.mocksris.controller.dto.BeneficiaryDto;
import global.govstack.mocksris.model.Beneficiary;
import global.govstack.mocksris.service.PaymentHubService;
import global.govstack.mocksris.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
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
public class PaymentRestController {
    private final PaymentConfigProperties paymentConfigProperties;
    private final PaymentService paymentService;

    private final PaymentHubService paymentHubService;
    private final ModelMapper modelMapper;

    public PaymentRestController(PaymentConfigProperties paymentConfigProperties, PaymentService paymentService, PaymentHubService paymentHubService, ModelMapper modelMapper) {
        this.paymentConfigProperties = paymentConfigProperties;
        this.paymentService = paymentService;
        this.paymentHubService = paymentHubService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/health")
    public String getHealth() {
        if(Objects.equals(paymentConfigProperties.mode(), "paymenthub")) {
            return paymentHubService.health();
        }
        return paymentService.health();
    }

    @PostMapping(value = "/order-payment")
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody final List<BeneficiaryDto> beneficiaryDtos) {
        List<Beneficiary> list = beneficiaryDtos.stream().map(this::convertToEntity).toList();
        paymentService.orderPayment(list);
        return "Thank you! Payment order received!";
    }

    private Beneficiary convertToEntity(BeneficiaryDto beneficiaryDto)  {
        return modelMapper.map(beneficiaryDto, Beneficiary.class);
    }
}

