package global.govstack.mocksris.service;

import global.govstack.mocksris.model.Beneficiary;
import global.govstack.mocksris.types.PaymentOnboardingCallbackMode;
import java.util.List;

public interface PaymentService {
  String health();

  void registerBeneficiary(List<Beneficiary> beneficiaries);

  void updateBeneficiary(List<Beneficiary> beneficiaries);

  void orderPayment(List<Beneficiary> beneficiaries);

  void updatePaymentOnboardingStatus(
      List<String> failedFunctionalIds,
      String requestId,
      PaymentOnboardingCallbackMode onboardingMode);
}
