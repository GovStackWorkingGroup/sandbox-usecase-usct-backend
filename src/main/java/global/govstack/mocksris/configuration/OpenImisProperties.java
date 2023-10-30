package global.govstack.mocksris.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "open-imis")
public record OpenImisProperties(String url) {}
