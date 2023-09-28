package global.govstack.mocksris.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PaymentHubOnboardingBeneficiaryDTO {
  @JsonProperty("RequestID")
  private String requestID = null;

  @JsonProperty("Beneficiaries")
  @Valid
  private List<PaymentHubOnboardingBeneficiaryDetailsDTO> beneficiaries = null;

  public PaymentHubOnboardingBeneficiaryDTO(
      String requestID, List<PaymentHubOnboardingBeneficiaryDetailsDTO> beneficiaries) {
    this.requestID = requestID;
    this.beneficiaries = beneficiaries;
  }

  public PaymentHubOnboardingBeneficiaryDTO requestID(String requestID) {
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

  public PaymentHubOnboardingBeneficiaryDTO beneficiaries(
      List<PaymentHubOnboardingBeneficiaryDetailsDTO> beneficiaries) {
    this.beneficiaries = beneficiaries;
    return this;
  }

  public PaymentHubOnboardingBeneficiaryDTO addBeneficiariesItem(
      PaymentHubOnboardingBeneficiaryDetailsDTO beneficiariesItem) {
    if (this.beneficiaries == null) {
      this.beneficiaries = new ArrayList<PaymentHubOnboardingBeneficiaryDetailsDTO>();
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
  public List<PaymentHubOnboardingBeneficiaryDetailsDTO> getBeneficiaries() {
    return beneficiaries;
  }

  public void setBeneficiaries(List<PaymentHubOnboardingBeneficiaryDetailsDTO> beneficiaries) {
    this.beneficiaries = beneficiaries;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentHubOnboardingBeneficiaryDTO paymentOnboardingBeneficiaryDTO =
        (PaymentHubOnboardingBeneficiaryDTO) o;
    return Objects.equals(this.requestID, paymentOnboardingBeneficiaryDTO.requestID)
        && Objects.equals(this.beneficiaries, paymentOnboardingBeneficiaryDTO.beneficiaries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestID, beneficiaries);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentHubOnboardingBeneficiaryDTO {\n");

    sb.append("    requestID: ").append(toIndentedString(requestID)).append("\n");
    sb.append("    beneficiaries: ").append(toIndentedString(beneficiaries)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
