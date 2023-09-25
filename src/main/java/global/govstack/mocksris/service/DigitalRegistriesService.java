package global.govstack.mocksris.service;

import global.govstack.mocksris.configuration.DigitalRegistriesBBInformationMediatorProperties;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DigitalRegistriesService {

  private final HttpHeaders httpHeaders;
  private final DigitalRegistriesBBInformationMediatorProperties
      digitalRegistriesBBInformationMediatorProperties;

  public DigitalRegistriesService(
      DigitalRegistriesBBInformationMediatorProperties
          digitalRegistriesBBInformationMediatorProperties) {
    this.digitalRegistriesBBInformationMediatorProperties =
        digitalRegistriesBBInformationMediatorProperties;
    httpHeaders = new HttpHeaders();
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.add("X-Road-Client", digitalRegistriesBBInformationMediatorProperties.header());
  }

  public String health() {
    return new RestTemplate()
        .exchange(
            digitalRegistriesBBInformationMediatorProperties.baseUrl() + "/actuator/health",
            HttpMethod.GET,
            new HttpEntity<>(null, httpHeaders),
            String.class)
        .getBody();
  }
}
