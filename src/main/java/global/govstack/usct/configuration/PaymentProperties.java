package global.govstack.usct.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "payment.variables")
public record PaymentProperties(String sourceBbId, String governmentIdentifier) {}
