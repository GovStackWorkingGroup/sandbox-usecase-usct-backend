package global.govstack.usct.controller;

import global.govstack.usct.controller.dto.CandidateDto;
import global.govstack.usct.service.CandidateService;
import global.govstack.usct.service.ConsentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@PreAuthorize("hasAnyRole('REGISTRY_OFFICER','ENROLLMENT_OFFICER')")
public class ConsentController {

  private final ConsentService consentService;
  private final CandidateService candidateService;

  public ConsentController(ConsentService consentService, CandidateService candidateService) {
    this.consentService = consentService;
      this.candidateService = candidateService;
  }

  @PostMapping("/consent")
  @ResponseStatus(HttpStatus.CREATED)
  String requestForConsent(@RequestBody CandidateDto candidateDto) {
    log.info("Create consent for candidateId: {}", candidateDto.getId());
    var candidate = candidateService.getById(candidateDto.getId());
    if (candidate.isPresent()) {
      return consentService.save(candidate.get());
    }
    log.error(HttpStatus.NOT_FOUND + "Invalid is absent");
    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid is absent");
  }
}
