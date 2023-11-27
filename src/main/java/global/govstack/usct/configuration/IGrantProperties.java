package global.govstack.usct.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "igrant")
public record IGrantProperties(String url, String token, String mode, String dataAgreementId) {}
