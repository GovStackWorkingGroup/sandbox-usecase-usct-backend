package global.govstack.mocksris.service;

import global.govstack.mocksris.configuration.OpenImisProperties;
import global.govstack.mocksris.controller.dto.OpenImisPackageSet;
import global.govstack.mocksris.controller.dto.PackageDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.utils.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OpenImisService {

    private final RestTemplate restTemplate;
    private final OpenImisProperties openImisProperties;

    public OpenImisService(OpenImisProperties openImisProperties) {
        this.openImisProperties = openImisProperties;
        this.restTemplate = new RestTemplate();
    }

    public List<PackageDto> getAll() {
        log.info("Get all packages from OpenIMIS URL: {}", openImisProperties.url());
        try {
            OpenImisPackageSet packagesSet =
                    restTemplate
                            .exchange(openImisProperties.url(),
                                    HttpMethod.GET,
                                    new HttpEntity<>(createHeaders("admin", "govstack")),
                                    OpenImisPackageSet.class)
                            .getBody();
            return packagesSet.results().stream().map(PackageDto::new).toList();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
        }
    }

    public Optional<PackageDto> getById(int id) {
        log.info("Get package from OpenIMIS");
        try {
            OpenImisPackageSet packagesSet =
                    restTemplate
                            .exchange(openImisProperties.url(),
                                    HttpMethod.GET,
                                    new HttpEntity<>(createHeaders("admin", "govstack")),
                                    OpenImisPackageSet.class)
                            .getBody();

            return packagesSet.results().stream().map(PackageDto::new).findFirst();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
        }
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
