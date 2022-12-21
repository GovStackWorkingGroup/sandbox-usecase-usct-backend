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
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    RestTemplate template;

    @Value("#{listClientsUrl}")
    String listClientsUrl;

    @Value("#{headerValue}")
    String headerValue;

    @GetMapping("listClients")
    public XRoadClients listClients() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException, IOException, CertificateException {

        ResponseEntity<XRoadClients> response = template.exchange(listClientsUrl, HttpMethod.GET, httpEntity(), XRoadClients.class);
        XRoadClients clients = response.getBody();
        return clients;
    }

    private HttpEntity httpEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Road-Client", headerValue);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity entity = new HttpEntity(headers);
        return entity;
    }
}
