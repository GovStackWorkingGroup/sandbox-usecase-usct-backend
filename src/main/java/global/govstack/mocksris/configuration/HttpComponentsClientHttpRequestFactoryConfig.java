package global.govstack.mocksris.configuration;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import javax.net.ssl.SSLContext;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.NoopHostnameVerifier;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

@Configuration
public class HttpComponentsClientHttpRequestFactoryConfig {

  @Bean
  public HttpComponentsClientHttpRequestFactory getHttpComponentsClientHttpRequestFactory()
      throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
    TrustStrategy acceptingTrustStrategy = (x509Certificates, s) -> true;
    SSLContext sslContext =
        SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();
    SSLConnectionSocketFactory csf =
        new SSLConnectionSocketFactory(sslContext, new NoopHostnameVerifier());
    HttpComponentsClientHttpRequestFactory requestFactory =
        new HttpComponentsClientHttpRequestFactory();
    HttpClientConnectionManager connectionManager =
        PoolingHttpClientConnectionManagerBuilder.create().setSSLSocketFactory(csf).build();
    CloseableHttpClient httpClient =
        HttpClients.custom().setConnectionManager(connectionManager).build();
    requestFactory.setHttpClient(httpClient);
    return requestFactory;
  }
}
