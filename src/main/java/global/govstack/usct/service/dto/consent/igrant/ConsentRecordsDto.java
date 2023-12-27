package global.govstack.usct.service.dto.consent.igrant;

import java.util.List;

public class ConsentRecordsDto {
  private List<RecordDto> consentRecords;

  private PaginationDto pagination;

  public ConsentRecordsDto() {}

  public List<RecordDto> getConsentRecords() {
    return consentRecords;
  }

  public void setConsentRecords(List<RecordDto> consentRecords) {
    this.consentRecords = consentRecords;
  }

  public PaginationDto getPagination() {
    return pagination;
  }

  public void setPagination(PaginationDto pagination) {
    this.pagination = pagination;
  }
}
