package global.govstack.mocksris.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.*;
import java.util.Objects;

public class PaymentOnboardingBeneficiaryDetailsDTO {
  @JsonProperty("PayeeFunctionalID")
  private String payeeFunctionalID = null;

  @JsonProperty("PaymentModality")
  private String paymentModality = null;

  @JsonProperty("FinancialAddress")
  private String financialAddress = null;

  public PaymentOnboardingBeneficiaryDetailsDTO(
      String functionalId, String financialModality, String financialAddress) {
    this.payeeFunctionalID = functionalId;
    this.paymentModality = financialModality;
    this.financialAddress = financialAddress;
  }

  public PaymentOnboardingBeneficiaryDetailsDTO payeeFunctionalID(String payeeFunctionalID) {
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

  public PaymentOnboardingBeneficiaryDetailsDTO paymentModality(String paymentModality) {
    this.paymentModality = paymentModality;
    return this;
  }

  /**
   * Get paymentModality
   *
   * @return paymentModality
   */
  @ApiModelProperty(value = "")
  @Size(min = 2, max = 2)
  public String getPaymentModality() {
    return paymentModality;
  }

  public void setPaymentModality(String paymentModality) {
    this.paymentModality = paymentModality;
  }

  public PaymentOnboardingBeneficiaryDetailsDTO financialAddress(String financialAddress) {
    this.financialAddress = financialAddress;
    return this;
  }

  /**
   * Get financialAddress
   *
   * @return financialAddress
   */
  @ApiModelProperty(value = "")
  @Size(max = 30)
  public String getFinancialAddress() {
    return financialAddress;
  }

  public void setFinancialAddress(String financialAddress) {
    this.financialAddress = financialAddress;
  }

  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentOnboardingBeneficiaryDetailsDTO paymentOnboardingBeneficiaryDetailsDTO =
        (PaymentOnboardingBeneficiaryDetailsDTO) o;
    return Objects.equals(
            this.payeeFunctionalID, paymentOnboardingBeneficiaryDetailsDTO.payeeFunctionalID)
        && Objects.equals(
            this.paymentModality, paymentOnboardingBeneficiaryDetailsDTO.paymentModality)
        && Objects.equals(
            this.financialAddress, paymentOnboardingBeneficiaryDetailsDTO.financialAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(payeeFunctionalID, paymentModality, financialAddress);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegisterbeneficiaryBeneficiaries {\n");

    sb.append("    payeeFunctionalID: ").append(toIndentedString(payeeFunctionalID)).append("\n");
    sb.append("    paymentModality: ").append(toIndentedString(paymentModality)).append("\n");
    sb.append("    financialAddress: ").append(toIndentedString(financialAddress)).append("\n");
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
