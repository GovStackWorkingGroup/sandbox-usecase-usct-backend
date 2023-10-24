package global.govstack.usct.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "paymenthub.bb")
public record PaymentHubProperties(
    String accountMapperURL,
    String bulkConnectorURL,
    String callbackBaseUrl,
    String registeringInstitutionId,
    String tenant,
    String programId,
    String jwsTenantPrivateKey,
    String paymentMode) {}
