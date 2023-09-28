package global.govstack.mocksris.controller;

import com.fasterxml.jackson.annotation.JsonValue;
import global.govstack.mocksris.controller.dto.RolesDto;
import global.govstack.mocksris.service.TokenService;
import java.security.Principal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class AuthController {
  record Token(@JsonValue String token) {}

  private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

  private final TokenService tokenService;

  public AuthController(TokenService tokenService) {
    this.tokenService = tokenService;
  }

  @PostMapping("/token")
  public ResponseEntity<Token> token(Authentication authentication) {
    LOG.debug("Token requested for user: '{}'", authentication.getName());
    String token = tokenService.generateToken(authentication);
    LOG.debug("Token granted: {}", token);
    return ResponseEntity.ok(new Token(token));
  }

  @GetMapping("/roles")
  public RolesDto getRoles(Principal principal) {
    return tokenService.getRoles(principal);
  }
}
