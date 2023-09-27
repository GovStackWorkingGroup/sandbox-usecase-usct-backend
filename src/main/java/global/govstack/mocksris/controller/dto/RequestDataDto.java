package global.govstack.mocksris.controller.dto;

import java.util.List;
import java.util.Map;

public class RequestDataDto {

  private String method;
  private Map<String, List<String>> headers;
  private Map<String, String[]> parameters;
  private String body;

  public String getMethod() {
    return method;
  }

  public RequestDataDto setMethod(String method) {
    this.method = method;
    return this;
  }

  public Map<String, List<String>> getHeaders() {
    return headers;
  }

  public RequestDataDto setHeaders(Map<String, List<String>> headers) {
    this.headers = headers;
    return this;
  }

  public Map<String, String[]> getParameters() {
    return parameters;
  }

  public RequestDataDto setParameters(Map<String, String[]> parameters) {
    this.parameters = parameters;
    return this;
  }

  public String getBody() {
    return body;
  }

  public RequestDataDto setBody(String body) {
    this.body = body;
    return this;
  }
}
