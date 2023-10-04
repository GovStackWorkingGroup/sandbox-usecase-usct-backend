package global.govstack.mocksris.types;

public enum PaymentModality {
  BANK_ACCOUNT("00"),
  MOBILE_MONEY("01");

  private final String code;

  PaymentModality(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}
