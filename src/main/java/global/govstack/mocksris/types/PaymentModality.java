package global.govstack.mocksris.types;

public enum PaymentModality {
  BANK_ACCOUNT("00", "accountNumber", "mojaloop"),
  MOBILE_MONEY("01", "msisdn", "mojaloop");

  private final String code;
  private final String key;
  private final String paymentMode;

  PaymentModality(String code, String key, String paymentMode) {
    this.code = code;
    this.key = key;
    this.paymentMode = paymentMode;
  }

  public String getKey() {
    return key;
  }

  public String getCode() {
    return code;
  }

  public String paymentMode() {
    return paymentMode;
  }
}
