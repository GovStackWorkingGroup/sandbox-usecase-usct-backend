package global.govstack.mocksris.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PaymentDTO {
  @JsonProperty("RequestID")
  private String requestID = null;

  @JsonProperty("SourceBBID")
  private String sourceBBID = null;

  @JsonProperty("BatchID")
  private String batchID = null;

  @JsonProperty("CreditInstructions")
  @Valid
  private List<PaymentCreditInstructionsDTO> creditInstructions = null;

  public PaymentDTO(
      String requestId,
      String sourceBbId,
      String batchId,
      List<PaymentCreditInstructionsDTO> creditInstructionsDTO) {
    this.requestID = requestId;
    this.sourceBBID = sourceBbId;
    this.batchID = batchId;
    this.creditInstructions = creditInstructionsDTO;
  }

  public PaymentDTO requestID(String requestID) {
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

  public PaymentDTO sourceBBID(String sourceBBID) {
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

  public PaymentDTO batchID(String batchID) {
    this.batchID = batchID;
    return this;
  }

  /**
   * Get batchID
   *
   * @return batchID
   */
  @ApiModelProperty(value = "")
  @Size(min = 12, max = 12)
  public String getBatchID() {
    return batchID;
  }

  public void setBatchID(String batchID) {
    this.batchID = batchID;
  }

  public PaymentDTO creditInstructions(List<PaymentCreditInstructionsDTO> creditInstructions) {
    this.creditInstructions = creditInstructions;
    return this;
  }

  public PaymentDTO addCreditInstructionsItem(PaymentCreditInstructionsDTO creditInstructionsItem) {
    if (this.creditInstructions == null) {
      this.creditInstructions = new ArrayList<PaymentCreditInstructionsDTO>();
    }
    this.creditInstructions.add(creditInstructionsItem);
    return this;
  }

  /**
   * Get creditInstructions
   *
   * @return creditInstructions
   */
  @ApiModelProperty(value = "")
  @Valid
  public List<PaymentCreditInstructionsDTO> getCreditInstructions() {
    return creditInstructions;
  }

  public void setCreditInstructions(List<PaymentCreditInstructionsDTO> creditInstructions) {
    this.creditInstructions = creditInstructions;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentDTO bulkpaymentDTO = (PaymentDTO) o;
    return Objects.equals(this.requestID, bulkpaymentDTO.requestID)
        && Objects.equals(this.sourceBBID, bulkpaymentDTO.sourceBBID)
        && Objects.equals(this.batchID, bulkpaymentDTO.batchID)
        && Objects.equals(this.creditInstructions, bulkpaymentDTO.creditInstructions);
  }

  @Override
  public int hashCode() {
    return Objects.hash(requestID, sourceBBID, batchID, creditInstructions);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BulkpaymentBody {\n");

    sb.append("    requestID: ").append(toIndentedString(requestID)).append("\n");
    sb.append("    sourceBBID: ").append(toIndentedString(sourceBBID)).append("\n");
    sb.append("    batchID: ").append(toIndentedString(batchID)).append("\n");
    sb.append("    creditInstructions: ").append(toIndentedString(creditInstructions)).append("\n");
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
