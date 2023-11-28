package global.govstack.usct.service;

import global.govstack.usct.controller.dto.ConsentDto;
import global.govstack.usct.model.Candidate;
import global.govstack.usct.model.Consent;
import global.govstack.usct.repositories.ConsentRepository;
import global.govstack.usct.types.ConsentStatus;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@ConditionalOnProperty(name = "igrant.mode", havingValue = "local")
@Service
public class ConsentServiceMock implements ConsentService {

  private final ConsentRepository consentRepository;

  public Optional<ConsentDto> getConsent(Candidate candidate) {
    return consentRepository.findConsentByCandidateId(candidate).map(ConsentDto::new);
  }

  public ConsentServiceMock(ConsentRepository consentRepository) {
    this.consentRepository = consentRepository;
  }

  public String save(Candidate candidate) {
    Consent consent = new Consent();
    consent.setCandidateId(candidate);
    consent.setStatus(ConsentStatus.GRANTED);
    consent.setDate(LocalDateTime.now());
    consentRepository.save(consent);
    return "Consent was granted";
  }

  public void deleteByCandidateId(Candidate candidate) {
    consentRepository.deleteByCandidateId(candidate);
  }
}
