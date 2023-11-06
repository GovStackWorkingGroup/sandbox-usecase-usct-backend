package global.govstack.usct.filter;

import global.govstack.usct.configuration.CallbackProperties;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class CallbackIpFilter extends OncePerRequestFilter {

  private final List<String> cidrs;

  public CallbackIpFilter(CallbackProperties callbackProperties) {
    cidrs = callbackProperties.cidr();
  }

  private final List<AntPathRequestMatcher> ipProtectedPaths =
      List.of(new AntPathRequestMatcher("/api/v1/callback/**"));

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {
    if (matchNotUrl(request) || matches(request, cidrs)) {
      filterChain.doFilter(request, response);
    } else {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }
  }

  private boolean matches(HttpServletRequest request, List<String> subnets) {
    var check = subnets.stream().anyMatch(subnet -> new IpAddressMatcher(subnet).matches(request));
    return check;
  }

  private boolean matchNotUrl(HttpServletRequest request) {
    var check = ipProtectedPaths.stream().noneMatch(p -> p.matches(request));
    return check;
  }
}
