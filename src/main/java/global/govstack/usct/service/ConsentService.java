package global.govstack.usct.service;

import global.govstack.usct.controller.dto.ConsentDto;
import global.govstack.usct.model.Candidate;

public interface ConsentService {

  ConsentDto getConsent(Candidate candidate);

  String save(Candidate candidate);

  void deleteByCandidateId(Candidate candidate);
}
