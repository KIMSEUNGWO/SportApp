package app.sport.sw.filter;

import app.sport.sw.component.JwtUtil;
import app.sport.sw.component.SecurityUtil;
import app.sport.sw.domain.group.Club;
import app.sport.sw.dto.Response;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.exception.TokenException;
import app.sport.sw.response.ResponseCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final SecurityUtil securityUtil;
    private final ObjectMapper mapper = new ObjectMapper();

    private final List<String> excludePath = List.of(
        "/favicon.ico",
        "/social/login",
        "/register",
        "/social/token",
        "/images/",
        "/distinct/"
    );
    private final List<Pattern> excludePatterns = List.of(
        Pattern.compile("^/club/[0-9]+$"), // CLUB_INFO_PATTERN
        Pattern.compile("^/club/[0-9]+/users"),
        Pattern.compile("^/club/[0-9]+/board")
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("JwtAuthorizationFilter 시작");

        try {
            checkAccessTokenValid(request);
            filterChain.doFilter(request, response);
        } catch (TokenException e) {
            String requestURI = request.getRequestURI();
            if (isExcludedPattern(request)) {
                filterChain.doFilter(request, response);
            } else {
                System.out.println("TokenException 발생!! :" + e.getMessage());
                setErrorResponse(response, e.getTokenError());
            }
        }


    }

    private boolean isExcludedPattern(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String method = request.getMethod();
        for (Pattern pattern : excludePatterns) {
            if ("GET".equalsIgnoreCase(method) && pattern.matcher(requestURI).matches()) return true;
        }
        return false;
    }

    private void setErrorResponse(HttpServletResponse response, ResponseCode responseCode) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Response result = new Response(responseCode);
        try{
            response.getWriter()
                .write(mapper.writeValueAsString(result));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void checkAccessTokenValid(HttpServletRequest request) {
        String accessToken = jwtUtil.extractTokenFromHeader(request);
        jwtUtil.validateAccessToken(accessToken);
        securityUtil.saveUserInSecurityContext(accessToken);
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        if (path.equals("/")) return true;
        return excludePath.stream().anyMatch(path::startsWith);
    }
}
