package global.govstack.usct.service;

import global.govstack.usct.controller.dto.ConsentDto;
import global.govstack.usct.model.Candidate;
import java.util.Optional;

public interface ConsentService {

  Optional<ConsentDto> getConsent(Candidate candidate);

  String save(Candidate candidate);

  void deleteByCandidateId(Candidate candidate);
}
