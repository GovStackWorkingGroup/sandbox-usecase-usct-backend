package global.govstack.mocksris.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "digitalregistries.bb.im")
public record DigitalRegistriesBBInformationMediatorProperties(String baseUrl, String header) {}
