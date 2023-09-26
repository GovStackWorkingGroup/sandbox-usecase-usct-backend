package global.govstack.mocksris.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "payment.config")
public record PaymentConfigProperties(String mode) {}