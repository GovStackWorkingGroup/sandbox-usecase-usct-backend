package global.govstack.mocksris.controller;

import global.govstack.mocksris.controller.dto.CandidateDto;
import global.govstack.mocksris.controller.dto.RelativeDto;
import global.govstack.mocksris.model.Candidate;
import global.govstack.mocksris.service.CandidateService;
import global.govstack.mocksris.service.HouseholdInformationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class CandidateController {

    private final CandidateService candidateService;
    private final HouseholdInformationService householdInformationService;


    public CandidateController(CandidateService candidateService, ModelMapper modelMapper, HouseholdInformationService householdInformationService) {
        this.candidateService = candidateService;
        this.householdInformationService = householdInformationService;
    }

    @GetMapping("/candidates")
    public List<CandidateDto> getAll() {
        return candidateService.findAll()
                .stream()
                .map(CandidateDto::new)
                .toList();
    }

    @GetMapping("/relatives/{id}")
    public List<RelativeDto> getAllRelatives(@PathVariable("id") int id) {
        Candidate candidate = candidateService.findById(id);
        return householdInformationService
                .findAllRelatives(candidate)
                .stream()
                .map(RelativeDto::new)
                .toList();
    }

    @GetMapping("/candidates/{id}")
    public CandidateDto getCandidate(@PathVariable("id") int id) {
        Candidate candidate = candidateService.findById(id);
        return new CandidateDto(candidate);
    }
}
