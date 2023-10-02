package global.govstack.mocksris.service.dto.paymenthub;

public record PaymentHubBeneficiaryOnboardingFailedCallbackDTO(
    String payeeIdentity, String paymentModality, String failureReason) {}
