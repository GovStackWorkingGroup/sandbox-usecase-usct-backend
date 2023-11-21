package global.govstack.usct.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "open-imis")
public record OpenImisProperties(
    String url, String user, String password, String emulatorUrl, String mode, String header) {}
