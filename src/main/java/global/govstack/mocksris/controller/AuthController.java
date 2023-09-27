package global.govstack.mocksris.controller;

import global.govstack.mocksris.controller.dto.RolesDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Slf4j
@PreAuthorize("hasAnyRole('PAYMENT_OFFICER','ENROLLMENT_OFFICER','REGISTRY_ADMINISTRATION')")
public class AuthController {

  @GetMapping("/roles")
  public RolesDto getRoles(OAuth2AuthenticationToken principal) {
    return new RolesDto(
        principal.getPrincipal().getAttribute("email"),
        principal.getAuthorities().stream().map(Object::toString).toList());
  }
}
