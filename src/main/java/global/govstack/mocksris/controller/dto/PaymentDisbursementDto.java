package global.govstack.mocksris.controller.dto;

import global.govstack.mocksris.model.PaymentDisbursement;

public class PaymentDisbursementDto {

  private Long id = null;
  private String mode = null;
  private String requestUUID = null;
  private String request = null;
  private String response = null;
  private String callback = null;

  public PaymentDisbursementDto() {}

  public PaymentDisbursementDto(PaymentDisbursement paymentDisbursement) {
    this.id = paymentDisbursement.getId();
    this.mode = paymentDisbursement.getMode();
    this.requestUUID = paymentDisbursement.getRequestUUID();
    this.request = paymentDisbursement.getRequest();
    this.response = paymentDisbursement.getResponse();
    this.callback = paymentDisbursement.getCallback();
  }

  public Long getId() {
    return id;
  }

  public PaymentDisbursementDto setId(Long id) {
    this.id = id;
    return this;
  }

  public String getMode() {
    return mode;
  }

  public PaymentDisbursementDto setMode(String mode) {
    this.mode = mode;
    return this;
  }

  public String getRequestUUID() {
    return requestUUID;
  }

  public PaymentDisbursementDto setRequestUUID(String requestUUID) {
    this.requestUUID = requestUUID;
    return this;
  }

  public String getRequest() {
    return request;
  }

  public PaymentDisbursementDto setRequest(String request) {
    this.request = request;
    return this;
  }

  public String getResponse() {
    return response;
  }

  public PaymentDisbursementDto setResponse(String response) {
    this.response = response;
    return this;
  }

  public String getCallback() {
    return callback;
  }

  public PaymentDisbursementDto setCallback(String callback) {
    this.callback = callback;
    return this;
  }
}
