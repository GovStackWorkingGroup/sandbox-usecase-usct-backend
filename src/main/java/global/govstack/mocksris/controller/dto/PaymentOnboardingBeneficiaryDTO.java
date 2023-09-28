package global.govstack.mocksris.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PaymentOnboardingBeneficiaryDTO {
  @JsonProperty("RequestID")
  private String requestID = null;

  @JsonProperty("SourceBBID")
  private String sourceBBID = null;

  @JsonProperty("Beneficiaries")
  @Valid
  private List<PaymentOnboardingBeneficiaryDetailsDTO> beneficiaries = null;

  public PaymentOnboardingBeneficiaryDTO(
      String requestID,
      String sourceBBID,
      List<PaymentOnboardingBeneficiaryDetailsDTO> beneficiaries) {
    this.requestID = requestID;
    this.sourceBBID = sourceBBID;
    this.beneficiaries = beneficiaries;
  }

  public PaymentOnboardingBeneficiaryDTO requestID(String requestID) {
    this.requestID = requestID;
    return this;
  }

  /**
   * Get requestID
   *
   * @return requestID
   */
  @ApiModelProperty(value = "")
  @Size(min = 12, max = 12)
  public String getRequestID() {
    return requestID;
  }

  public void setRequestID(String requestID) {
    this.requestID = requestID;
  }

  public PaymentOnboardingBeneficiaryDTO sourceBBID(String sourceBBID) {
    this.sourceBBID = sourceBBID;
    return this;
  }

  /**
   * Get sourceBBID
   *
   * @return sourceBBID
   */
  @ApiModelProperty(value = "")
  @Size(min = 12, max = 12)
  public String getSourceBBID() {
    return sourceBBID;
  }

  public void setSourceBBID(String sourceBBID) {
    this.sourceBBID = sourceBBID;
  }

  public PaymentOnboardingBeneficiaryDTO beneficiaries(
      List<PaymentOnboardingBeneficiaryDetailsDTO> beneficiaries) {
    this.beneficiaries = beneficiaries;
    return this;
  }

  public PaymentOnboardingBeneficiaryDTO addBeneficiariesItem(
      PaymentOnboardingBeneficiaryDetailsDTO beneficiariesItem) {
    if (this.beneficiaries == null) {
      this.beneficiaries = new ArrayList<PaymentOnboardingBeneficiaryDetailsDTO>();
    }
    this.beneficiaries.add(beneficiariesItem);
    return this;
  }

  /**
   * Get beneficiaries
   *
   * @return beneficiaries
   */
  @ApiModelProperty(value = "")
  @Valid
  public List<PaymentOnboardingBeneficiaryDetailsDTO> getBeneficiaries() {
    return beneficiaries;
  }

  public void setBeneficiaries(List<PaymentOnboardingBeneficiaryDetailsDTO> beneficiaries) {
    this.beneficiaries = beneficiaries;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentOnboardingBeneficiaryDTO paymentOnboardingBeneficiaryDTO =
        (PaymentOnboardingBeneficiaryDTO) o;
    return Objects.equals(this.requestID, paymentOnboardingBeneficiaryDTO.requestID)
        && Objects.equals(this.sourceBBID, paymentOnboardingBeneficiaryDTO.sourceBBID)
        && Objects.equals(this.beneficiaries, paymentOnboardingBeneficiaryDTO.beneficiaries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestID, sourceBBID, beneficiaries);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentOnboardingBeneficiaryDTO {\n");

    sb.append("    requestID: ").append(toIndentedString(requestID)).append("\n");
    sb.append("    sourceBBID: ").append(toIndentedString(sourceBBID)).append("\n");
    sb.append("    beneficiaries: ").append(toIndentedString(beneficiaries)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
