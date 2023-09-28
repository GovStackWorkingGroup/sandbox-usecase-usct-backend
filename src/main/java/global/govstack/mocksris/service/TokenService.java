package global.govstack.mocksris.service;

import global.govstack.mocksris.controller.dto.RolesDto;
import java.security.Principal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

  private final JwtEncoder encoder;
  private final InMemoryUserDetailsManager userManager;

  public TokenService(JwtEncoder encoder, InMemoryUserDetailsManager userManager) {
    this.encoder = encoder;
    this.userManager = userManager;
  }

  public String generateToken(Authentication authentication) {
    Instant now = Instant.now();
    String scope =
        authentication.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.joining(" "));
    JwtClaimsSet claims =
        JwtClaimsSet.builder()
            .issuer("self")
            .issuedAt(now)
            .expiresAt(now.plus(1, ChronoUnit.HOURS))
            .subject(authentication.getName())
            .claim("scope", scope)
            .build();
    return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
  }

  public RolesDto getRoles(Principal principal) {
    var currentUser = userManager.loadUserByUsername(principal.getName());
    var listOfRoles =
        currentUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
    return new RolesDto(listOfRoles);
  }
}
