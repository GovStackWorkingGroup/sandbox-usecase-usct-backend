package global.govstack.usct.controller;

import global.govstack.usct.controller.dto.CandidateDto;
import global.govstack.usct.model.Candidate;
import global.govstack.usct.service.ConsentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@PreAuthorize("hasAnyRole('REGISTRY_OFFICER','ENROLLMENT_OFFICER')")
public class ConsentController {

  private final ConsentService consentService;
  private final ModelMapper modelMapper;

  public ConsentController(ConsentService consentService, ModelMapper modelMapper) {
    this.consentService = consentService;
    this.modelMapper = modelMapper;
  }

  @PostMapping("/consent")
  @ResponseStatus(HttpStatus.CREATED)
  String requestForConsent(@RequestBody CandidateDto candidateDto) {
    var candidate = convertToEntity(candidateDto);
    return consentService.save(candidate);
  }

  private Candidate convertToEntity(CandidateDto candidateDto) {
    return modelMapper.map(candidateDto, Candidate.class);
  }
}
