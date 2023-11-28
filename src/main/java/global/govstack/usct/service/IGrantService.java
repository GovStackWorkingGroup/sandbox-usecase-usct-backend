package global.govstack.usct.service;

import global.govstack.usct.configuration.IGrantProperties;
import global.govstack.usct.controller.dto.ConsentDto;
import global.govstack.usct.model.Candidate;
import global.govstack.usct.service.dto.consent.igrant.ConsentRecordsDto;
import global.govstack.usct.service.dto.consent.igrant.RecordDto;
import global.govstack.usct.types.ConsentStatus;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@ConditionalOnProperty(name = "igrant.mode", havingValue = "igrant")
public class IGrantService implements ConsentService {

  private final RestTemplate restTemplate;
  private final HttpHeaders httpHeaders;
  private final IGrantProperties properties;
  private final RestTemplate restTemplateSelfSigned;
  private final HttpComponentsClientHttpRequestFactory requestFactory;

  public IGrantService(
      IGrantProperties properties, HttpComponentsClientHttpRequestFactory requestFactory) {
    this.properties = properties;
    this.requestFactory = requestFactory;
    this.restTemplate = new RestTemplate();
    this.httpHeaders = new HttpHeaders();
    this.restTemplateSelfSigned = new RestTemplate(requestFactory);
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    httpHeaders.add("X-Road-Client", "application/json");
    httpHeaders.add("Authorization", properties.token());
  }

  public Optional<ConsentDto> getConsent(Candidate candidate) {
    log.info("Get consent from IGrant URL: {}", properties.url());
    httpHeaders.add("X-ConsentBB-IndividualId", candidate.getIGrantId());
    try {
      ConsentRecordsDto response =
          restTemplateSelfSigned
              .exchange(
                  properties.url() + "consent-record",
                  HttpMethod.GET,
                  new HttpEntity<>(httpHeaders),
                  ConsentRecordsDto.class)
              .getBody();
      Optional<RecordDto> recordDto = response.getConsentRecords().stream().findFirst();
      if (recordDto.get().optIn) {
        // todo it is necessary to extend the logic by obtaining a timestamp
        // https://consent-bb-swagger.igrant.io/v2023.11.1/index.html#get-/service/verification/consent-record/-consentRecordId-
        return Optional.of(new ConsentDto(ConsentStatus.GRANTED, null));
      } else {
        return Optional.of(new ConsentDto(ConsentStatus.NOT_GRANTED, null));
      }
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
    }
  }

  public String save(Candidate candidate) {
    log.info("Create consent for candidateId: {}", candidate.getId());
    httpHeaders.add("X-ConsentBB-IndividualId", candidate.getIGrantId());
    try {
      restTemplateSelfSigned.exchange(
          properties.url() + "data-agreement/" + properties.dataAgreementId(),
          HttpMethod.POST,
          new HttpEntity<>(httpHeaders),
          String.class);
      return "Consent request was successfully";
    } catch (Exception ex) {
      throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, ex.getMessage());
    }
  }

  public void deleteByCandidateId(Candidate candidate) {
    //    No need to implement
    //    Only local implementation of consent logic need this functionality.
  }
}
