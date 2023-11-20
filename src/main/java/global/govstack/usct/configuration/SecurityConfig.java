package global.govstack.usct.configuration;

import static global.govstack.usct.configuration.AuthoritiesConstants.ENROLLMENT_OFFICER;
import static global.govstack.usct.configuration.AuthoritiesConstants.PAYMENT_OFFICER;
import static global.govstack.usct.configuration.AuthoritiesConstants.REGISTRY_OFFICER;
import static org.springframework.security.config.Customizer.withDefaults;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;
import global.govstack.usct.util.JwtHttpMessageConverter;
import global.govstack.usct.util.OAuth2JwtUserRequestEntityConverter;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.text.ParseException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.client.endpoint.DefaultAuthorizationCodeTokenResponseClient;
import org.springframework.security.oauth2.client.endpoint.NimbusJwtClientAuthenticationParametersConverter;
import org.springframework.security.oauth2.client.endpoint.OAuth2AuthorizationCodeGrantRequestEntityConverter;
import org.springframework.security.oauth2.client.http.OAuth2ErrorResponseErrorHandler;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizationRequestResolver;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.jwt.JwtDecoderFactory;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Slf4j
public class SecurityConfig {

  public static final String CLAIMS =
      """
      {"userinfo":{
       "name":{"essential":true},
       "phone_number":{"essential":false},
       "email":{"essential":true},
       "picture":{"essential":false},
       "gender":{"essential":false},
       "birthdate":{"essential":false},
       "address":{"essential":false}},
       "id_token":{}
       }
      """;

  private final RsaKeyProperties jwtConfigProperties;

  public SecurityConfig(RsaKeyProperties jwtConfigProperties) {
    this.jwtConfigProperties = jwtConfigProperties;
  }

  @Order(1)
  @Bean
  SecurityFilterChain swaggerSecurityFilterChain(HttpSecurity http) throws Exception {
    return http.securityMatcher(
            "/swagger-ui/**",
            "/v3/api-docs/**",
            "/api-docs/**",
            "/api/v1/callback/**",
            "/api/authmode")
        .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
        .build();
  }

  Function<ClientRegistration, JWK> jwkResolver(Environment env)
      throws KeyStoreException,
          IOException,
          CertificateException,
          NoSuchAlgorithmException,
          JOSEException {
    final JWK jwk;
    final char[] password = env.getProperty("oidc.jwk-keystore-password", "").toCharArray();
    var keystore = KeyStore.getInstance("PKCS12");
    try (var is = new ClassPathResource("/esignet-oidc.p12").getInputStream()) {
      keystore.load(is, password);
      var alias = keystore.aliases().nextElement();
      jwk = JWK.load(keystore, alias, password);
    }
    return (clientRegistration) -> {
      if (clientRegistration
              .getClientAuthenticationMethod()
              .equals(ClientAuthenticationMethod.PRIVATE_KEY_JWT)
          && "esignet".equals(clientRegistration.getClientName())) {
        return jwk;
      }
      return null;
    };
  }

  @Bean
  @Order(2)
  @ConditionalOnProperty(name = "usct.authentication", havingValue = "mosip")
  SecurityFilterChain tokenSecurityFilterChain(
      HttpSecurity http, ClientRegistrationRepository clientRegistrationRepository, Environment env)
      throws Exception {
    log.info("Using OIDC authentication");

    var jwkResolver = jwkResolver(env);

    return http.securityMatcher("/api/**")
        .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
        .cors(withDefaults())
        .csrf(AbstractHttpConfigurer::disable)
        .logout(
            customizer ->
                customizer
                    .logoutUrl("/api/logout")
                    .logoutSuccessHandler(
                        new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                    .deleteCookies("USCT_SESSION"))
        .oauth2Login(
            customizer -> {
              customizer.loginProcessingUrl("/api/login/oauth2/code/*");
              customizer.successHandler(new SimpleUrlAuthenticationSuccessHandler("/driver-poc"));
              customizer.failureHandler(
                  new SimpleUrlAuthenticationFailureHandler("/driver-poc/login?error"));
              customizer.authorizationEndpoint(
                  ae -> {
                    ae.baseUri("/api/oauth2/authorization");
                    var resolver =
                        new DefaultOAuth2AuthorizationRequestResolver(
                            clientRegistrationRepository, "/api/oauth2/authorization");
                    resolver.setAuthorizationRequestCustomizer(
                        r ->
                            r.additionalParameters(
                                Map.of(
                                    "acr_values",
                                    "mosip:idp:acr:generated-code",
                                    "claims",
                                    CLAIMS)));
                    ae.authorizationRequestResolver(resolver);
                  });
              customizer.userInfoEndpoint(
                  ue -> ue.userAuthoritiesMapper(SecurityConfig::mapAuthorities));
              customizer.tokenEndpoint(
                  te -> {
                    var requestEntityConverter =
                        new OAuth2AuthorizationCodeGrantRequestEntityConverter();
                    requestEntityConverter.addParametersConverter(
                        new NimbusJwtClientAuthenticationParametersConverter<>(jwkResolver));
                    var tokenResponseClient = new DefaultAuthorizationCodeTokenResponseClient();
                    tokenResponseClient.setRequestEntityConverter(requestEntityConverter);
                    te.accessTokenResponseClient(tokenResponseClient);
                  });
            })
        .exceptionHandling(
            ex -> ex.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
        .build();
  }

  @Bean
  @Order(2)
  @ConditionalOnExpression("'${usct.authentication:local}' ne 'mosip'")
  SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {
    log.info("Using local authentication");

    return http.securityMatcher("/api/**")
        .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
        .cors(withDefaults())
        .csrf(AbstractHttpConfigurer::disable)
        .logout(
            customizer ->
                customizer
                    .logoutUrl("/api/logout")
                    .logoutSuccessHandler(
                        new HttpStatusReturningLogoutSuccessHandler(HttpStatus.OK))
                    .deleteCookies("USCT_SESSION"))
        .userDetailsService(
            new InMemoryUserDetailsManager(
                User.withUsername("enrollment-officer")
                    .password("{noop}password")
                    .roles(ENROLLMENT_OFFICER)
                    .build(),
                User.withUsername("payment-officer")
                    .password("{noop}password")
                    .roles(PAYMENT_OFFICER)
                    .build(),
                User.withUsername("registry-officer")
                    .password("{noop}password")
                    .roles(REGISTRY_OFFICER)
                    .build()))
        .formLogin(
            customizer -> {
              customizer.loginProcessingUrl("/api/login");
              customizer.successHandler(
                  ((request, response, authentication) -> {
                    response.setStatus(200);
                  }));
              customizer.failureHandler(
                  ((request, response, exception) -> {
                    response.sendError(
                        HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
                  }));
            })
        .exceptionHandling(
            ex -> ex.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
        .build();
  }

  private static Collection<? extends GrantedAuthority> mapAuthorities(
      Collection<? extends GrantedAuthority> authorities) {
    for (var authority : authorities) {
      if (authority instanceof OidcUserAuthority oidc) {
        var subject = oidc.getIdToken().getSubject();
        log.info("Logging in user {}", subject);
        return switch (subject) {
          case "268505314334796284434550524121540566" -> Set.of(
              new SimpleGrantedAuthority("ROLE_" + REGISTRY_OFFICER));
          case "299950323465436931629862208523254959" -> Set.of(
              new SimpleGrantedAuthority("ROLE_" + ENROLLMENT_OFFICER));
          case "294629625538148508290996199782510910" -> Set.of(
              new SimpleGrantedAuthority("ROLE_" + PAYMENT_OFFICER));
          default -> Set.of();
        };
      }
    }
    return Set.of();
  }

  @Bean
  public OAuth2UserService<OidcUserRequest, OidcUser> oAuth2UserService(
      JwtDecoderFactory<ClientRegistration> df) {
    var userService = new DefaultOAuth2UserService();
    var restTemplate = new RestTemplate();

    restTemplate.setErrorHandler(new OAuth2ErrorResponseErrorHandler());
    restTemplate.getMessageConverters().add(new JwtHttpMessageConverter(df.createDecoder(null)));
    userService.setRestOperations(restTemplate);
    userService.setRequestEntityConverter(new OAuth2JwtUserRequestEntityConverter());

    var oidcUserService = new OidcUserService();
    oidcUserService.setOauth2UserService(userService);
    return oidcUserService;
  }

  @Bean
  public JwtDecoderFactory<ClientRegistration> jwtDecoderFactory()
      throws IOException, ParseException {
    // Another hack to work around the problematic jwks document returned by the API.
    // Spring Security does not directly support file urls:s as jwk-set-uri

    final JWKSet jwkSet;
    jwkSet = JWKSet.load(new ClassPathResource("/mosip-jwks.json").getInputStream());
    return context -> {
      final JWKSource<SecurityContext> jwkSource = new ImmutableJWKSet<>(jwkSet);
      final ConfigurableJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();
      final JWSKeySelector<SecurityContext> jwsKeySelector =
          new JWSVerificationKeySelector<>(JWSAlgorithm.RS256, jwkSource);
      jwtProcessor.setJWSKeySelector(jwsKeySelector);
      jwtProcessor.setJWTClaimsSetVerifier((claims, ctx) -> {});
      return new NimbusJwtDecoder(jwtProcessor);
    };
  }

  @Bean
  JwtEncoder jwtEncoder() {
    JWK jwk =
        new RSAKey.Builder(jwtConfigProperties.publicKey())
            .privateKey(jwtConfigProperties.privateKey())
            .build();
    return new NimbusJwtEncoder(new ImmutableJWKSet<>(new JWKSet(jwk)));
  }
}
