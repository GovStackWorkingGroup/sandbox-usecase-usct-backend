package global.govstack.mocksris.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "payment.bb.im")
public record InformationMediatorProperties(String baseUrl, String header) {}