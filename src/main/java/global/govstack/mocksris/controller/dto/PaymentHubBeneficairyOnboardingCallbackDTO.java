package global.govstack.mocksris.controller.dto;

import java.util.List;

public record PaymentHubBeneficairyOnboardingCallbackDTO(
    String requestID,
    String registerRequestID,
    int numberFailedCases,
    List<PaymentHubBeneficairyOnboardingFailedCallbackDTO> failedCases) {}
