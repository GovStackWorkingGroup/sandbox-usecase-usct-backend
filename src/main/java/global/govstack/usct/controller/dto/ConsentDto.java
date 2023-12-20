package global.govstack.usct.controller.dto;

import global.govstack.usct.model.Consent;
import global.govstack.usct.types.ConsentStatus;
import java.time.LocalDateTime;
import lombok.Getter;

public class ConsentDto {

  private Integer id;
  private String originalId;
  private Integer candidateId;

  @Getter private ConsentStatus status;

  @Getter private LocalDateTime date;

  public ConsentDto() {}

  public ConsentDto(Consent consent) {
    this.id = consent.getId();
    this.candidateId = consent.getCandidate().getId();
    this.status = consent.getStatus();
    this.date = consent.getDate();
  }

  public ConsentDto(ConsentStatus consentStatus, LocalDateTime dateTime) {
    this.status = consentStatus;
    this.date = dateTime;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getCandidateId() {
    return candidateId;
  }

  public void setCandidateId(Integer candidateId) {
    this.candidateId = candidateId;
  }

  public void setStatus(ConsentStatus status) {
    this.status = status;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }
}
