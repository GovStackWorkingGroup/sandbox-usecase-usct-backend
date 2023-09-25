package global.govstack.mocksris.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.*;
import java.util.Objects;

public class PaymentResponseDTO {
  @JsonProperty("ResponseCode")
  private String responseCode = null;

  @JsonProperty("ResponseDescription")
  private String responseDescription = null;

  @JsonProperty("RequestID")
  private String requestID = null;

  public PaymentResponseDTO responseCode(String responseCode) {
    this.responseCode = responseCode;
    return this;
  }

  /**
   * Get responseCode
   *
   * @return responseCode
   */
  @ApiModelProperty(value = "")
  @Size(min = 2, max = 2)
  public String getResponseCode() {
    return responseCode;
  }

  public void setResponseCode(String responseCode) {
    this.responseCode = responseCode;
  }

  public PaymentResponseDTO responseDescription(String responseDescription) {
    this.responseDescription = responseDescription;
    return this;
  }

  /**
   * Get responseDescription
   *
   * @return responseDescription
   */
  @ApiModelProperty(value = "")
  @Size(max = 200)
  public String getResponseDescription() {
    return responseDescription;
  }

  public void setResponseDescription(String responseDescription) {
    this.responseDescription = responseDescription;
  }

  public PaymentResponseDTO requestID(String requestID) {
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

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentResponseDTO paymentResponseDTO = (PaymentResponseDTO) o;
    return Objects.equals(this.responseCode, paymentResponseDTO.responseCode)
        && Objects.equals(this.responseDescription, paymentResponseDTO.responseDescription)
        && Objects.equals(this.requestID, paymentResponseDTO.requestID);
  }

  @Override
  public int hashCode() {
    return Objects.hash(responseCode, responseDescription, requestID);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class InlineResponse200 {\n");

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
