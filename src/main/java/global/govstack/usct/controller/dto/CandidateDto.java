package global.govstack.usct.controller.dto;

import global.govstack.usct.model.Candidate;
import java.util.List;

public class CandidateDto {
  private int id;
  private PersonDto person;
  private List<PackageDto> packages;

  public CandidateDto() {}

  public CandidateDto(Candidate candidate, List<PackageDto> packages) {
    this.id = candidate.getId();
    this.person = new PersonDto(candidate.getPerson());
    this.packages = packages;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public PersonDto getPerson() {
    return person;
  }

  public void setPerson(PersonDto person) {
    this.person = person;
  }

  public List<PackageDto> getPackages() {
    return packages;
  }

  public void setPackages(List<PackageDto> packages) {
    this.packages = packages;
  }
}
