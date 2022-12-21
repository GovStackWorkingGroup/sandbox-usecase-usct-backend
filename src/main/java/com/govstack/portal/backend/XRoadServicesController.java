package com.govstack.portal.backend;

import com.govstack.portal.backend.models.XRoadClient;
import com.govstack.portal.backend.models.XRoadClientId;
import com.govstack.portal.backend.models.XRoadClients;
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
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;

@RestController
@RequestMapping("/api/v1/")
public class XRoadServicesController {

    @Value("${xroad.central}")
    private String xroadCentral;

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

    @GetMapping("listClients")
    public XRoadClients listClients() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException, CertificateException {
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
        final HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create()
                .setSSLSocketFactory(sslSocketFactory)
                .setDefaultTlsConfig(TlsConfig.custom()
                        .setHandshakeTimeout(Timeout.ofSeconds(30))
                        .setSupportedProtocols(TLS.V_1_3)
                        .build())
                .build();
        HttpClient client = HttpClients.custom()
                .setConnectionManager(cm)
                .build();

        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(client);

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Road-Client", getHeaderValue());
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity entity = new HttpEntity(headers);
        RestTemplate template = new RestTemplate(requestFactory);
        ResponseEntity<XRoadClients> response = template.exchange(getListClientsUrl(), HttpMethod.GET, entity, XRoadClients.class);
        XRoadClients clients = response.getBody();
        for(XRoadClient xRoadClient : clients.getMember() ){
            System.out.println("client: " +xRoadClient);
        }
        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());

        return clients;
    }

    private String getHeaderValue(){
        StringBuilder url = new StringBuilder();
        url.append(instance);
        url.append("/" +className);
        url.append("/"+member);
        url.append("/"+subsystem);
        return url.toString();
    }
    private String getListClientsUrl() {
        StringBuilder url = new StringBuilder();
        url.append("https://");
        url.append("localhost:1443/");
        url.append("r1/" + instance);
        url.append("/" +className);
        url.append("/"+member);
        url.append("/"+subsystem);
        url.append("/"+serviceCode);
        url.append("/listClients");
        return url.toString();
    }
}
