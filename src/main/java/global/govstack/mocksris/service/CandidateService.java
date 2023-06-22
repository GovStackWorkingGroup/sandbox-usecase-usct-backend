package global.govstack.mocksris.service;

import global.govstack.mocksris.model.Candidate;
import global.govstack.mocksris.repositories.CandidateRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {
    private final CandidateRepository candidateRepository;

    public CandidateService(CandidateRepository candidateRepository) {
        this.candidateRepository = candidateRepository;
    }

    public List<Candidate> findAll() {
        return candidateRepository.findAll();

    }

    public Candidate findById(int id) {
        return candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate with id: " + id + " doesn't exist"));
    }

    public Candidate save(Candidate candidate) {
        return candidateRepository.save(candidate);
    }
}
