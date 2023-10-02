package global.govstack.mocksris.service.dto.paymenthub;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentHubOrderPaymentPartyDTO {
  @JsonProperty("key")
  private String key;

  @JsonProperty("value")
  private String value;

  public String getKey() {
    return key;
  }

  public PaymentHubOrderPaymentPartyDTO(String key, String value) {
    this.key = key;
    this.value = value;
  }

  public PaymentHubOrderPaymentPartyDTO setKey(String key) {
    this.key = key;
    return this;
  }

  public String getValue() {
    return value;
  }

  public PaymentHubOrderPaymentPartyDTO setValue(String value) {
    this.value = value;
    return this;
  }
}
