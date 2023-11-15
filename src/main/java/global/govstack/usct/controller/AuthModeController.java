package global.govstack.usct.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authmode")
@Slf4j
@PreAuthorize("permitAll()")
public class AuthModeController {

  final String authMode;

  public AuthModeController(@Value("${usct.authentication}") String authMode) {
    this.authMode = authMode;
  }

  @GetMapping
  String authMode() {
    return authMode;
  }
}
