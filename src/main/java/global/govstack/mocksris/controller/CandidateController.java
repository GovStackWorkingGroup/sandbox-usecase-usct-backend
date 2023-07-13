package global.govstack.mocksris.controller;

import global.govstack.mocksris.controller.dto.CandidateDto;
import global.govstack.mocksris.model.Candidate;
import global.govstack.mocksris.service.CandidateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class CandidateController {

    private final CandidateService candidateService;


    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @GetMapping("/candidates")
    public List<CandidateDto> getAll() {
        return candidateService.findAll()
                .stream()
                .map(CandidateDto::new)
                .toList();
    }

    @GetMapping("/candidates/{id}")
    public CandidateDto getCandidate(@PathVariable("id") int id) {
        Candidate candidate = candidateService.findById(id);
        return new CandidateDto(candidate);
    }
}
