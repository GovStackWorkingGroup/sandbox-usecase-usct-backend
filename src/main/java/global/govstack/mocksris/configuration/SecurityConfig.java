package global.govstack.mocksris.configuration;

import static global.govstack.mocksris.configuration.AuthoritiesConstants.ENROLLMENT_OFFICER;
import static global.govstack.mocksris.configuration.AuthoritiesConstants.PAYMENT_OFFICER;
import static global.govstack.mocksris.configuration.AuthoritiesConstants.REGISTRY_ADMINISTRATION;
import static org.springframework.security.config.Customizer.withDefaults;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

  private static final String JWT_PART = "SCOPE_";
  private static final String ROLE = JWT_PART + "ROLE_";

  private final RsaKeyProperties jwtConfigProperties;

  public SecurityConfig(RsaKeyProperties jwtConfigProperties) {
    this.jwtConfigProperties = jwtConfigProperties;
  }

  @Bean
  public InMemoryUserDetailsManager user() {
    return new InMemoryUserDetailsManager(
        User.withUsername("enrollment-officer")
            .password("{noop}password")
            .roles(ENROLLMENT_OFFICER)
            .build(),
        User.withUsername("payment-officer")
            .password("{noop}password")
            .roles(PAYMENT_OFFICER)
            .build(),
        User.withUsername("registry-administration")
            .password("{noop}password")
            .roles(REGISTRY_ADMINISTRATION)
            .build());
  }

  @Order(1)
  @Bean
  SecurityFilterChain swaggerSecurityFilterChain(HttpSecurity http) throws Exception {
    return http.securityMatcher("/swagger-ui/**", "/v3/api-docs/**", "/api-docs/**")
        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
        .build();
  }

  @Order(2)
  @Bean
  SecurityFilterChain tokenSecurityFilterChain(HttpSecurity http) throws Exception {
    return http.securityMatcher(new AntPathRequestMatcher("/api/v1/token"))
        .cors(withDefaults())
        .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .csrf(AbstractHttpConfigurer::disable)
        .exceptionHandling(
            ex -> {
              ex.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint());
              ex.accessDeniedHandler(new BearerTokenAccessDeniedHandler());
            })
        .httpBasic(withDefaults())
        .build();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http.cors(withDefaults())
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(
            authorize ->
                authorize
                    .requestMatchers(HttpMethod.POST, "/api/v1/candidates/**")
                    .hasAuthority(ROLE + REGISTRY_ADMINISTRATION)
                    .requestMatchers(HttpMethod.POST, "/api/v1/candidates/**")
                    .hasAuthority(ROLE + REGISTRY_ADMINISTRATION)
                    .requestMatchers(HttpMethod.DELETE, "/api/v1/candidates/**")
                    .hasAuthority(ROLE + REGISTRY_ADMINISTRATION)
                    .requestMatchers(HttpMethod.GET, "/api/v1/candidates/**")
                    .hasAnyAuthority(ROLE + ENROLLMENT_OFFICER, ROLE + REGISTRY_ADMINISTRATION)
                    .requestMatchers("/api/v1/callback")
                    .permitAll()
                    .requestMatchers("/api/v1/beneficiary-register-callback")
                    .permitAll()
                    .requestMatchers("/api/v1/beneficiary-update-callback")
                    .permitAll()
                    .anyRequest()
                    .authenticated())
        .sessionManagement(
            session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .oauth2ResourceServer(configurer -> configurer.jwt(withDefaults()))
        .csrf(AbstractHttpConfigurer::disable)
        .exceptionHandling(
            (ex) ->
                ex.authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                    .accessDeniedHandler(new BearerTokenAccessDeniedHandler()))
        .build();
  }

  @Bean
  JwtDecoder jwtDecoder() {
    return NimbusJwtDecoder.withPublicKey(jwtConfigProperties.publicKey()).build();
  }

  @Bean
  JwtEncoder jwtEncoder() {
    JWK jwk =
        new RSAKey.Builder(jwtConfigProperties.publicKey())
            .privateKey(jwtConfigProperties.privateKey())
            .build();
    JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
    return new NimbusJwtEncoder(jwks);
  }
}
