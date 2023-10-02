package global.govstack.mocksris.controller.dto;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import global.govstack.mocksris.model.PaymentDisbursement;

public class PaymentDisbursementDto {

  private ObjectMapper objectMapper = new ObjectMapper();

  private Long id = null;
  private String mode = null;
  private String requestUUID = null;
  private JsonNode request = null;
  private JsonNode response = null;
  private JsonNode callback = null;

  public PaymentDisbursementDto() {}

  public PaymentDisbursementDto(PaymentDisbursement paymentDisbursement) {
    this.id = paymentDisbursement.getId();
    this.mode = paymentDisbursement.getMode();
    this.requestUUID = paymentDisbursement.getRequestUUID();
    this.request = parseIfNotNull(paymentDisbursement.getRequest());
    this.response = parseIfNotNull(paymentDisbursement.getResponse());
    this.callback = parseIfNotNull(paymentDisbursement.getCallback());
  }

  private JsonNode parseIfNotNull(String input) {
    try {
      return objectMapper.readTree(input);
    } catch (IllegalArgumentException e) {
      return null;
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
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

  public JsonNode getRequest() {
    return request;
  }

  public PaymentDisbursementDto setRequest(JsonNode request) {
    this.request = request;
    return this;
  }

  public JsonNode getResponse() {
    return response;
  }

  public PaymentDisbursementDto setResponse(JsonNode response) {
    this.response = response;
    return this;
  }

  public JsonNode getCallback() {
    return callback;
  }

  public PaymentDisbursementDto setCallback(JsonNode callback) {
    this.callback = callback;
    return this;
  }
}
