package global.govstack.usct.controller;

import global.govstack.usct.controller.dto.RolesDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@Slf4j
@PreAuthorize("hasAnyRole('PAYMENT_OFFICER','ENROLLMENT_OFFICER','REGISTRY_OFFICER')")
public class AuthController {

  @GetMapping("/roles")
  public RolesDto getRoles(Authentication authentication) {

    var email = authentication.getName();
    var name = authentication.getName();

    if (authentication instanceof OAuth2AuthenticationToken t) {
      email = t.getPrincipal().getAttribute("email");
      name = t.getPrincipal().getAttribute("name");
    }

    return new RolesDto(
        email, name, authentication.getAuthorities().stream().map(Object::toString).toList());
  }
}
