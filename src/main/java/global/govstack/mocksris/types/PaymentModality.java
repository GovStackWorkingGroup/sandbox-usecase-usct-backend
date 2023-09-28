package global.govstack.mocksris.types;

public enum PaymentModality {
  BANK_ACCOUNT("00", "accountNumber"),
  MOBILE_MONEY("01", "msisdn");

  private final String code;
  private final String key;

  PaymentModality(String code, String key) {
    this.code = code;
    this.key = key;
  }

  public String getKey() {
    return key;
  }

  public String getCode() {
    return code;
  }
}
