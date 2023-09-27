package global.govstack.mocksris.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Size;
import java.util.Objects;

public class PaymentCreditInstructionsDTO {
  @JsonProperty("InstructionID")
  private String instructionID = null;

  @JsonProperty("PayeeFunctionalID")
  private String payeeFunctionalID = null;

  @JsonProperty("Amount")
  private Float amount = null;

  @JsonProperty("Currency")
  private String currency = null;

  @JsonProperty("Narration")
  private String narration = null;

  public PaymentCreditInstructionsDTO(
      String instructionID,
      String payeeFunctionalId,
      Float amount,
      String currency,
      String narration) {
    this.instructionID = instructionID;
    this.payeeFunctionalID = payeeFunctionalId;
    this.amount = amount;
    this.currency = currency;
    this.narration = narration;
  }

  public PaymentCreditInstructionsDTO instructionID(String instructionID) {
    this.instructionID = instructionID;
    return this;
  }

  /**
   * Get instructionID
   *
   * @return instructionID
   */
  @ApiModelProperty(value = "")
  @Size(min = 16, max = 16)
  public String getInstructionID() {
    return instructionID;
  }

  public void setInstructionID(String instructionID) {
    this.instructionID = instructionID;
  }

  public PaymentCreditInstructionsDTO payeeFunctionalID(String payeeFunctionalID) {
    this.payeeFunctionalID = payeeFunctionalID;
    return this;
  }

  /**
   * Get payeeFunctionalID
   *
   * @return payeeFunctionalID
   */
  @ApiModelProperty(value = "")
  @Size(min = 20, max = 20)
  public String getPayeeFunctionalID() {
    return payeeFunctionalID;
  }

  public void setPayeeFunctionalID(String payeeFunctionalID) {
    this.payeeFunctionalID = payeeFunctionalID;
  }

  public PaymentCreditInstructionsDTO amount(Float amount) {
    this.amount = amount;
    return this;
  }

  /**
   * Get amount
   *
   * @return amount
   */
  @ApiModelProperty(value = "")
  public Float getAmount() {
    return amount;
  }

  public void setAmount(Float amount) {
    this.amount = amount;
  }

  public PaymentCreditInstructionsDTO currency(String currency) {
    this.currency = currency;
    return this;
  }

  /**
   * Get currency
   *
   * @return currency
   */
  @ApiModelProperty(value = "")
  @Size(min = 3, max = 3)
  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public PaymentCreditInstructionsDTO narration(String narration) {
    this.narration = narration;
    return this;
  }

  /**
   * Get narration
   *
   * @return narration
   */
  @ApiModelProperty(value = "")
  @Size(min = 1, max = 50)
  public String getNarration() {
    return narration;
  }

  public void setNarration(String narration) {
    this.narration = narration;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentCreditInstructionsDTO bulkpaymentCreditInstructionsDTO =
        (PaymentCreditInstructionsDTO) o;
    return Objects.equals(this.instructionID, bulkpaymentCreditInstructionsDTO.instructionID)
        && Objects.equals(
            this.payeeFunctionalID, bulkpaymentCreditInstructionsDTO.payeeFunctionalID)
        && Objects.equals(this.amount, bulkpaymentCreditInstructionsDTO.amount)
        && Objects.equals(this.currency, bulkpaymentCreditInstructionsDTO.currency)
        && Objects.equals(this.narration, bulkpaymentCreditInstructionsDTO.narration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(instructionID, payeeFunctionalID, amount, currency, narration);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BulkpaymentCreditInstructions {\n");

    sb.append("    instructionID: ").append(toIndentedString(instructionID)).append("\n");
    sb.append("    payeeFunctionalID: ").append(toIndentedString(payeeFunctionalID)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
    sb.append("    narration: ").append(toIndentedString(narration)).append("\n");
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
