package global.govstack.usct.service.dto.paymenthub;

public record PaymentHubBeneficiaryOnboardingFailedCallbackDTO(
    String payeeIdentity, String paymentModality, String failureReason) {}
