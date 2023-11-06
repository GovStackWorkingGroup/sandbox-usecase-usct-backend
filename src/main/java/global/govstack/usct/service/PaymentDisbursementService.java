package global.govstack.usct.service;

import global.govstack.usct.model.PaymentDisbursement;
import global.govstack.usct.repositories.PaymentDisbursementRepository;
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
