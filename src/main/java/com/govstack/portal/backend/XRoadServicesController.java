package com.govstack.portal.backend;

import com.govstack.portal.backend.models.XRoadClients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
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
