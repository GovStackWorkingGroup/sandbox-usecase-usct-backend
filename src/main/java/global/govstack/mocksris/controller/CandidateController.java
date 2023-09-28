package global.govstack.mocksris.controller;

import global.govstack.mocksris.controller.dto.CandidateDto;
import global.govstack.mocksris.controller.dto.CreateCandidateDto;
import global.govstack.mocksris.model.Candidate;
import global.govstack.mocksris.service.CandidateService;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class CandidateController {

  private final CandidateService candidateService;

  private final ModelMapper modelMapper;

  public CandidateController(CandidateService candidateService, ModelMapper modelMapper) {
    this.candidateService = candidateService;
    this.modelMapper = modelMapper;
  }

  @GetMapping("/candidates")
  public List<CandidateDto> getAll() {
    return candidateService.findAll().stream().map(CandidateDto::new).toList();
  }

  @GetMapping("/candidates/{id}")
  public CandidateDto getCandidate(@PathVariable("id") int id) {
    Candidate candidate = candidateService.findById(id);
    return new CandidateDto(candidate);
  }

  @PostMapping("/candidates")
  @ResponseStatus(HttpStatus.CREATED)
  CandidateDto createCandidate(@RequestBody final CreateCandidateDto candidateDto) {
    var newCandidate = candidateService.save(candidateDto);
    return convertToDto(newCandidate);
  }

  @PutMapping("/candidates/{id}")
  @ResponseStatus(HttpStatus.OK)
  CandidateDto updateCandidate(@RequestBody CandidateDto candidateDto) {
    if (candidateDto.getId() == 0) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id");
    }
    Candidate candidate = convertToEntity(candidateDto);
    var updatedCandidate = candidateService.save(candidate);
    return convertToDto(updatedCandidate);
  }

  @DeleteMapping("/candidates/{id}")
  @ResponseStatus(HttpStatus.OK)
  public void deleteCandidate(@PathVariable("id") int id) {
    candidateService.deleteById(id);
  }

  private Candidate convertToEntity(CandidateDto candidateDto) {
    return modelMapper.map(candidateDto, Candidate.class);
  }

  private Candidate convertToEntity(CreateCandidateDto candidateDto) {
    return modelMapper.map(candidateDto, Candidate.class);
  }

  private CandidateDto convertToDto(Candidate candidate) {
    return modelMapper.map(candidate, CandidateDto.class);
  }
}
