package global.govstack.usct.service;

import global.govstack.usct.configuration.OpenImisProperties;
import global.govstack.usct.controller.dto.digital.registries.MainResponseDto;
import global.govstack.usct.controller.dto.digital.registries.PackageDto;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@ConditionalOnProperty(name = "open-imis.mode", havingValue = "emulator")
public class DigitalRegistriesEmulator implements DigitalRegistriesService {

  private final HttpHeaders httpHeaders;

  private final OpenImisProperties openImisProperties;

  private final RestTemplate restTemplate;

  public DigitalRegistriesEmulator(OpenImisProperties openImisProperties) {
    this.openImisProperties = openImisProperties;
    this.restTemplate = new RestTemplate();

    httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.add("X-Road-Client", openImisProperties.header());
  }

  public List<PackageDto> getAll() {
    log.info("Get all packages from emulator URL: {}", openImisProperties.emulatorUrl());
    try {
      MainResponseDto packagesSet =
          restTemplate
              .exchange(
                  openImisProperties.emulatorUrl(),
                  HttpMethod.GET,
                  new HttpEntity<>(httpHeaders),
                  MainResponseDto.class)
              .getBody();
      return packagesSet.getResults();
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
    }
  }
}
