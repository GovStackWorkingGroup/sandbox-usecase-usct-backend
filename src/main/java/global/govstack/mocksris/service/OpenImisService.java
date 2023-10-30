package global.govstack.mocksris.service;

import global.govstack.mocksris.controller.dto.OpenImisPackageSet;
import global.govstack.mocksris.controller.dto.PackageDto;
import org.apache.hc.client5.http.utils.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class OpenImisService {

    private final RestTemplate restTemplate;

    public OpenImisService() {
        this.restTemplate = new RestTemplate();
    }

    public List<PackageDto> getAll() {
        OpenImisPackageSet packagesSet =
                restTemplate
                        .exchange("http://localhost:8000/data/registryname/111?search=package&filter=phone",
                                HttpMethod.GET,
                                new HttpEntity<>(createHeaders("admin", "govstack")),
                                OpenImisPackageSet.class)
                        .getBody();

        return packagesSet.results().stream().map(PackageDto::new).toList();
    }

    public Optional<PackageDto> getById(int id) {

        OpenImisPackageSet packagesSet =
                restTemplate
                        .exchange("http://localhost:8000/data/registryname/111?search=147&filter=ID",
                                HttpMethod.GET,
                                new HttpEntity<>(createHeaders("admin", "govstack")),
                                OpenImisPackageSet.class)
                        .getBody();

        return packagesSet.results().stream().map(PackageDto::new).findFirst();
    }

    HttpHeaders createHeaders(String username, String password) {
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(StandardCharsets.US_ASCII));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
        }};
    }
}
