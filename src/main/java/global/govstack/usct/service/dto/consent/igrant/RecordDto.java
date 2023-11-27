package global.govstack.usct.service.dto.consent.igrant;

public class RecordDto {

  public String id;
  public String dataAgreementId;
  public String dataAgreementRevisionId;
  public String dataAgreementRevisionHash;
  public String individualId;
  public Boolean optIn;
  public String state;
  public String signatureId;

  public RecordDto() {}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getDataAgreementId() {
    return dataAgreementId;
  }

  public void setDataAgreementId(String dataAgreementId) {
    this.dataAgreementId = dataAgreementId;
  }

  public String getDataAgreementRevisionId() {
    return dataAgreementRevisionId;
  }

  public void setDataAgreementRevisionId(String dataAgreementRevisionId) {
    this.dataAgreementRevisionId = dataAgreementRevisionId;
  }

  public String getDataAgreementRevisionHash() {
    return dataAgreementRevisionHash;
  }

  public void setDataAgreementRevisionHash(String dataAgreementRevisionHash) {
    this.dataAgreementRevisionHash = dataAgreementRevisionHash;
  }

  public String getIndividualId() {
    return individualId;
  }

  public void setIndividualId(String individualId) {
    this.individualId = individualId;
  }

  public Boolean getOptIn() {
    return optIn;
  }

  public void setOptIn(Boolean optIn) {
    this.optIn = optIn;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getSignatureId() {
    return signatureId;
  }

  public void setSignatureId(String signatureId) {
    this.signatureId = signatureId;
  }
}
