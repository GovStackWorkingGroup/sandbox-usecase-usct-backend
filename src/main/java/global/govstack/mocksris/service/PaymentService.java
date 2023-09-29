package global.govstack.mocksris.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import global.govstack.mocksris.model.Beneficiary;
import global.govstack.mocksris.model.PaymentDisbursement;
import global.govstack.mocksris.types.PaymentOnboardingCallbackMode;
import global.govstack.mocksris.types.PaymentOnboardingStatus;
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

  void updatePaymentOrderStatus(String callbackBody) throws JsonProcessingException;

  List<PaymentDisbursement> getPaymentDisbursements();

  default void validateOnboardingStatus(List<Beneficiary> beneficiaries) {
    beneficiaries.forEach(
        beneficiary -> {
          if (!beneficiary.getPaymentOnboardingStatus().equals(PaymentOnboardingStatus.ONBOARDED)) {
            throw new RuntimeException("Not every beneficiary is registered in Payment Service!");
          }
        });
  }
}
