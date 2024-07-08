package app.sport.sw.filter;

import app.sport.sw.component.JwtUtil;
import app.sport.sw.component.SecurityUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final SecurityUtil securityUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        String accessToken = jwtUtil.extractTokenFromHeader(request);
//        jwtUtil.validateAccessToken(accessToken);
//
//        securityUtil.saveUserInSecurityContext(accessToken);

        filterChain.doFilter(request, response);

    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String[] excludePath = {
            "/social/login",
            "/social/token",
            "/api-docs/json",
            "/api-docs",
            "/swagger-ui/",
            "/swagger-config",
            "/swagger.yaml",
            "/requestBodies",
            "/swagger-",
            "/error"
        };
        String path = request.getRequestURI();



        return Arrays.stream(excludePath).anyMatch(path::startsWith);
    }
}
