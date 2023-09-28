package global.govstack.mocksris.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.Size;
import java.util.Objects;

public class PaymentHubOnboardingBeneficiaryDetailsDTO {
  @JsonProperty("payeeIdentity")
  private String payeeIdentity = null;

  @JsonProperty("paymentModality")
  private String paymentModality = null;

  @JsonProperty("financialAddress")
  private String financialAddress = null;

  @JsonProperty("bankingInstitutionCode")
  private String bankingInstitutionCode = null;

  public PaymentHubOnboardingBeneficiaryDetailsDTO(
      String payeeIdentity,
      String financialModality,
      String financialAddress,
      String bankingInstitutionCode) {
    this.payeeIdentity = payeeIdentity;
    this.paymentModality = financialModality;
    this.financialAddress = financialAddress;
    this.bankingInstitutionCode = bankingInstitutionCode;
  }

  public PaymentHubOnboardingBeneficiaryDetailsDTO(
      String payeeIdentity, String financialModality, String financialAddress) {
    this.payeeIdentity = payeeIdentity;
    this.paymentModality = financialModality;
    this.financialAddress = financialAddress;
  }

  public PaymentHubOnboardingBeneficiaryDetailsDTO payeePayeeIdentity(String payeeIdentity) {
    this.payeeIdentity = payeeIdentity;
    return this;
  }

  /**
   * Get payeeIdentity
   *
   * @return payeeIdentity
   */
  @ApiModelProperty(value = "")
  @Size(min = 20, max = 20)
  public String getPayeeIdentity() {
    return payeeIdentity;
  }

  public void setPayeeIdentity(String payeeIdentity) {
    this.payeeIdentity = payeeIdentity;
  }

  public PaymentHubOnboardingBeneficiaryDetailsDTO paymentModality(String paymentModality) {
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

  public PaymentHubOnboardingBeneficiaryDetailsDTO financialAddress(String financialAddress) {
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

  public PaymentHubOnboardingBeneficiaryDetailsDTO bankingInstitutionCode(
      String bankingInstitutionCode) {
    this.bankingInstitutionCode = bankingInstitutionCode;
    return this;
  }

  /**
   * Get bankingInstitutionCode
   *
   * @return bankingInstitutionCode
   */
  @ApiModelProperty(value = "")
  @Size(max = 30)
  public String getBankingInstitutionCode() {
    return bankingInstitutionCode;
  }

  public void setBankingInstitutionCode(String bankingInstitutionCode) {
    this.bankingInstitutionCode = bankingInstitutionCode;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PaymentHubOnboardingBeneficiaryDetailsDTO paymentOnboardingBeneficiaryDetailsDTO =
        (PaymentHubOnboardingBeneficiaryDetailsDTO) o;
    return Objects.equals(this.payeeIdentity, paymentOnboardingBeneficiaryDetailsDTO.payeeIdentity)
        && Objects.equals(
            this.paymentModality, paymentOnboardingBeneficiaryDetailsDTO.paymentModality)
        && Objects.equals(
            this.financialAddress, paymentOnboardingBeneficiaryDetailsDTO.financialAddress)
        && Objects.equals(
            this.bankingInstitutionCode,
            paymentOnboardingBeneficiaryDetailsDTO.bankingInstitutionCode);
  }

  @Override
  public int hashCode() {
    return Objects.hash(payeeIdentity, paymentModality, financialAddress, bankingInstitutionCode);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PaymentHubOnboardingBeneficiaryDetailsDTO {\n");

    sb.append("    payeeIdentity: ").append(toIndentedString(payeeIdentity)).append("\n");
    sb.append("    paymentModality: ").append(toIndentedString(paymentModality)).append("\n");
    sb.append("    financialAddress: ").append(toIndentedString(financialAddress)).append("\n");
    sb.append("    bankingInstitutionCode: ")
        .append(toIndentedString(bankingInstitutionCode))
        .append("\n");
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
