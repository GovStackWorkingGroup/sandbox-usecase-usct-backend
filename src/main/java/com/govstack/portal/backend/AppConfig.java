package com.govstack.portal.backend;

import org.apache.hc.client5.http.classic.HttpClient;
import org.apache.hc.client5.http.config.TlsConfig;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.http.ssl.TLS;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.util.Timeout;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Configuration
public class AppConfig {

    @Value("${xroad.certcn}")
    private String xroadCertCN;

    @Value("${xroad.instance}")
    private String instance;

    @Value("${xroad.class}")
    private String className;

    @Value("${xroad.member}")
    private String member;

    @Value("${xroad.subsystem}")
    private String subsystem;

    @Value("${xroad.servicecode}")
    private String serviceCode;

    @Value("${xroad.servicename}")
    private String servicename;

    @Value("${xroad.serviceport}")
    private String serviceport;

    @Bean
    public RestTemplate restTemplate() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        final SSLContext sslcontext = SSLContexts.custom()
                .loadTrustMaterial((chain, authType) -> {
                    final X509Certificate cert = chain[0];
                    return xroadCertCN.equalsIgnoreCase(cert.getSubjectDN().getName());
                })
                .build();
        final SSLConnectionSocketFactory sslSocketFactory = SSLConnectionSocketFactoryBuilder.create()
                .setSslContext(sslcontext)
                .setHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                .build();
        // Allow TLSv1.3 protocol only
        final HttpClientConnectionManager connectionManager = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(sslSocketFactory)
                .setDefaultTlsConfig(TlsConfig.custom()
                        .setHandshakeTimeout(Timeout.ofSeconds(30))
                        .setSupportedProtocols(TLS.V_1_3)
                        .build())
                .build();
        HttpClient httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();

        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);

        return new RestTemplate(requestFactory);
    }

    @Bean
    public String listClientsUrl() {
        String url = "https://" +
                servicename + ":" + serviceport +
                "/r1/" + instance +
                "/" + className +
                "/" + member +
                "/" + subsystem +
                "/" + serviceCode +
                "/listClients";
        return url;
    }

    @Bean
    public String headerValue(){
        String url = instance +
                "/" + className +
                "/" + member +
                "/" + subsystem;
        return url;
    }
}
