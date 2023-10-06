package global.govstack.mocksris.service.dto.paymenthub;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class PaymentHubOrderPaymentDTO {

  @JsonProperty("requestId")
  private String requestId;

  @JsonProperty("creditParty")
  List<PaymentHubOrderPaymentPartyDTO> creditParty;

  @JsonProperty("paymentMode")
  private String paymentMode;

  @JsonProperty("amount")
  private Float amount;

  @JsonProperty("currency")
  private String currency;

  @JsonProperty("descriptionText")
  private String descriptionText;

  public PaymentHubOrderPaymentDTO(
      String requestId,
      List<PaymentHubOrderPaymentPartyDTO> creditParty,
      String paymentMode,
      Float amount,
      String currency,
      String descriptionText) {
    this.requestId = requestId;
    this.creditParty = creditParty;
    this.paymentMode = paymentMode;
    this.amount = amount;
    this.currency = currency;
    this.descriptionText = descriptionText;
  }

  public String getRequestId() {
    return requestId;
  }

  public PaymentHubOrderPaymentDTO setRequestId(String requestId) {
    this.requestId = requestId;
    return this;
  }

  public List<PaymentHubOrderPaymentPartyDTO> getCreditParty() {
    return creditParty;
  }

  public PaymentHubOrderPaymentDTO setCreditParty(
      List<PaymentHubOrderPaymentPartyDTO> creditParty) {
    this.creditParty = creditParty;
    return this;
  }

  public String getPaymentMode() {
    return paymentMode;
  }

  public PaymentHubOrderPaymentDTO setPaymentMode(String paymentMode) {
    this.paymentMode = paymentMode;
    return this;
  }

  public Float getAmount() {
    return amount;
  }

  public PaymentHubOrderPaymentDTO setAmount(Float amount) {
    this.amount = amount;
    return this;
  }

  public String getCurrency() {
    return currency;
  }

  public PaymentHubOrderPaymentDTO setCurrency(String currency) {
    this.currency = currency;
    return this;
  }

  public String getDescriptionText() {
    return descriptionText;
  }

  public PaymentHubOrderPaymentDTO setDescriptionText(String descriptionText) {
    this.descriptionText = descriptionText;
    return this;
  }
}
