package global.govstack.mocksris.service;

import global.govstack.mocksris.model.PaymentDisbursement;
import global.govstack.mocksris.repositories.PaymentDisbursementRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PaymentDisbursementService {
  private final PaymentDisbursementRepository paymentDisbursementRepository;

  public PaymentDisbursementService(PaymentDisbursementRepository paymentDisbursementRepository) {
    this.paymentDisbursementRepository = paymentDisbursementRepository;
  }

  public List<PaymentDisbursement> getPaymentDisbursements() {
    return paymentDisbursementRepository.findAll();
  }
}
