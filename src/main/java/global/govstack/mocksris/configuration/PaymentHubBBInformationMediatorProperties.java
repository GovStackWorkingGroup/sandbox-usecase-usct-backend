package global.govstack.mocksris.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "paymenthub.bb.im")
public record PaymentHubBBInformationMediatorProperties(String header) {}
