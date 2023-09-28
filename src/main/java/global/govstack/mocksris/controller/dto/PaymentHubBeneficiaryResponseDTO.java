package global.govstack.mocksris.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Size;
import java.util.Objects;

public class PaymentHubBeneficiaryResponseDTO {
  @JsonProperty("responseCode")
  private String responseCode = null;

  @JsonProperty("responseDescription")
  private String responseDescription = null;

  @JsonProperty("requestID")
  private String requestID = null;

  public PaymentHubBeneficiaryResponseDTO(
      String responseCode, String responseDescription, String requestID) {
    this.responseCode = responseCode;
    this.responseDescription = responseDescription;
    this.requestID = requestID;
  }

  public PaymentHubBeneficiaryResponseDTO responseCode(String responseCode) {
    this.responseCode = responseCode;
    return this;
  }

  /**
   * Get payeeFunctionalID
   *
   * @return payeeFunctionalID
   */
  @ApiModelProperty(value = "")
  @Size(min = 20, max = 20)
  public String getResponseCode() {
    return responseCode;
  }

  public void setResponseCode(String responseCode) {
    this.responseCode = responseCode;
  }

  public PaymentHubBeneficiaryResponseDTO responseDescription(String responseDescription) {
    this.responseDescription = responseDescription;
    return this;
  }

  /**
   * Get paymentModality
   *
   * @return paymentModality
   */
  @ApiModelProperty(value = "")
  @Size(min = 2, max = 2)
  public String getResponseDescription() {
    return responseDescription;
  }

  public void setResponseDescription(String responseDescription) {
    this.responseDescription = responseDescription;
  }

  public PaymentHubBeneficiaryResponseDTO requestID(String requestID) {
    this.requestID = requestID;
    return this;
  }

  /**
   * Get financialAddress
   *
   * @return financialAddress
   */
  @ApiModelProperty(value = "")
  @Size(max = 30)
  public String getRequestID() {
    return requestID;
  }

  public void setRequestID(String requestID) {
    this.requestID = requestID;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentHubBeneficiaryResponseDTO paymentHubBeneficiaryResponseDTO =
        (PaymentHubBeneficiaryResponseDTO) o;
    return Objects.equals(this.responseCode, paymentHubBeneficiaryResponseDTO.responseCode)
        && Objects.equals(
            this.responseDescription, paymentHubBeneficiaryResponseDTO.responseDescription)
        && Objects.equals(this.requestID, paymentHubBeneficiaryResponseDTO.requestID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseCode, responseDescription, requestID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentHubBeneficiaryResponseDTO {\n");

    sb.append("    responseCode: ").append(toIndentedString(responseCode)).append("\n");
    sb.append("    responseDescription: ")
        .append(toIndentedString(responseDescription))
        .append("\n");
    sb.append("    requestID: ").append(toIndentedString(requestID)).append("\n");
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
