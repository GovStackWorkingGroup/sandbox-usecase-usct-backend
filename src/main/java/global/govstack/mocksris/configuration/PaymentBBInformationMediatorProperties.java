package global.govstack.mocksris.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "payment.bb.im")
public record PaymentBBInformationMediatorProperties(String baseUrl, String header) {}
