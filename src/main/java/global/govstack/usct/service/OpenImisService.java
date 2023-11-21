package global.govstack.usct.service;

import global.govstack.usct.configuration.OpenImisProperties;
import global.govstack.usct.controller.dto.OpenImisPackageSet;
import global.govstack.usct.controller.dto.digital.registries.PackageDto;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.utils.Base64;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@ConditionalOnProperty(name = "open-imis.mode", havingValue = "open-imis")
public class OpenImisService implements DigitalRegistriesService {

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
              .exchange(
                  openImisProperties.url(),
                  HttpMethod.GET,
                  new HttpEntity<>(
                      createHeaders(openImisProperties.user(), openImisProperties.password())),
                  OpenImisPackageSet.class)
              .getBody();
      return packagesSet.results().stream().map(PackageDto::new).toList();
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
    }
  }

  HttpHeaders createHeaders(String username, String password) {
    return new HttpHeaders() {
      {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);
        set("Authorization", authHeader);
      }
    };
  }
}
