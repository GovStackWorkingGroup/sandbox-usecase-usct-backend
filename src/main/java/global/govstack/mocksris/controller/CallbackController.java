package global.govstack.mocksris.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import global.govstack.mocksris.controller.dto.RequestDataDto;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/callback")
@CrossOrigin
@PreAuthorize("hasAnyRole('PAYMENT_OFFICER','ENROLLMENT_OFFICER','REGISTRY_OFFICER')")
public class CallbackController {

  private static final Logger LOG = LoggerFactory.getLogger(CallbackController.class);

  private ObjectMapper objectMapper = new ObjectMapper();

  @PreAuthorize("permitAll()")
  @RequestMapping(value = "/callback")
  public RequestDataDto callback(HttpServletRequest request) throws IOException {
    Map<String, List<String>> headersMap =
        Collections.list(request.getHeaderNames()).stream()
            .collect(
                Collectors.toMap(
                    Function.identity(), h -> Collections.list(request.getHeaders(h))));

    RequestDataDto rq = new RequestDataDto();
    rq.setMethod(request.getMethod());
    rq.setHeaders(headersMap);
    rq.setParameters(request.getParameterMap());
    rq.setBody(request.getReader().lines().collect(Collectors.joining()));

    LOG.info(objectMapper.writeValueAsString(rq));

    return rq;
  }
}
