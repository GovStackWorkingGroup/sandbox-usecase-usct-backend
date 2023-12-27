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
    if (candidate.getConsent() != null) {
      return Optional.of(new ConsentDto(candidate.getConsent()));
    } else return Optional.empty();
  }

  public ConsentServiceMock(ConsentRepository consentRepository) {
    this.consentRepository = consentRepository;
  }

  public String save(Candidate candidate) {
    Consent consent = new Consent();
    consent.setCandidate(candidate);
    consent.setStatus(ConsentStatus.GRANTED);
    consent.setDate(LocalDateTime.now());
    consentRepository.save(consent);
    return "Consent was granted";
  }

  public void deleteByCandidateId(Candidate candidate) {
    log.info("Delete consent by CandidateId: {}", candidate.getPerson().getId());
    consentRepository.delete(candidate.getConsent());
  }
}
