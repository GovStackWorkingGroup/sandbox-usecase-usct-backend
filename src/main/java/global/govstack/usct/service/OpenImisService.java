package global.govstack.usct.service;

import global.govstack.usct.configuration.OpenImisProperties;
import global.govstack.usct.controller.dto.OpenImisPackageSet;
import global.govstack.usct.controller.dto.digital.registries.PackageDto;
import java.nio.charset.StandardCharsets;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.hc.client5.http.utils.Base64;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@ConditionalOnProperty(name = "open-imis.mode", havingValue = "open-imis")
public class OpenImisService implements DigitalRegistriesService {

  private final RestTemplate restTemplate;
  private final OpenImisProperties openImisProperties;
  private final HttpHeaders httpHeaders;

  public OpenImisService(OpenImisProperties openImisProperties) {
    this.openImisProperties = openImisProperties;
    this.restTemplate = new RestTemplate();
    httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.add("X-Road-Client", openImisProperties.header());
    httpHeaders.add("Authorization", encodeCredentials());
  }

  public List<PackageDto> getAll() {
    log.info("Get all packages from OpenIMIS URL: {}", openImisProperties.url());
    try {
      OpenImisPackageSet packagesSet =
          restTemplate
              .exchange(
                  openImisProperties.url() + openImisProperties.suffix(),
                  HttpMethod.GET,
                  new HttpEntity<>(httpHeaders),
                  OpenImisPackageSet.class)
              .getBody();
      return packagesSet.results().stream().map(PackageDto::new).toList();
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
    }
  }

  String encodeCredentials() {
    String auth = openImisProperties.user() + ":" + openImisProperties.password();
    byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
    return "Basic " + new String(encodedAuth);
  }
}
