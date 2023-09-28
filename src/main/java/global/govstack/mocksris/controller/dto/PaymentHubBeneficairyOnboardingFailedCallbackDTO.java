package global.govstack.mocksris.controller.dto;

public record PaymentHubBeneficairyOnboardingFailedCallbackDTO(
    String payeeIdentity, String paymentModality, String failureReason) {}
