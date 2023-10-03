package global.govstack.mocksris.model;

import jakarta.persistence.*;

@Entity
public class PaymentDisbursement {

  public PaymentDisbursement() {}

  public PaymentDisbursement(String mode, String requestUUID, String request, String response) {
    this.mode = mode;
    this.requestUUID = requestUUID;
    this.request = request;
    this.response = response;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "id", nullable = false)
  private Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  private String mode;

  public String getMode() {
    return mode;
  }

  public PaymentDisbursement setMode(String mode) {
    this.mode = mode;
    return this;
  }

  private String requestUUID;

  public String getRequestUUID() {
    return requestUUID;
  }

  public void setRequestUUID(String requestUUID) {
    this.requestUUID = requestUUID;
  }

  @Column(columnDefinition = "TEXT", length = 2048)
  private String request;

  public String getRequest() {
    return request;
  }

  public void setRequest(String request) {
    this.request = request;
  }

  @Column(columnDefinition = "TEXT", length = 2048)
  private String response;

  public String getResponse() {
    return response;
  }

  public PaymentDisbursement setResponse(String response) {
    this.response = response;
    return this;
  }

  @Column(columnDefinition = "TEXT", length = 2048)
  private String callback;

  public String getCallback() {
    return callback;
  }

  public PaymentDisbursement setCallback(String callback) {
    this.callback = callback;
    return this;
  }
}
