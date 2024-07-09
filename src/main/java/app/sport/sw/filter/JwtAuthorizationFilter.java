package app.sport.sw.filter;

import app.sport.sw.component.JwtUtil;
import app.sport.sw.component.SecurityUtil;
import app.sport.sw.dto.Response;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.exception.TokenException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final SecurityUtil securityUtil;
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("JwtAuthorizationFilter 시작");

        try {
            checkAccessTokenValid(request);
            filterChain.doFilter(request, response);
        } catch (TokenException e) {
            System.out.println("TokenException 발생!! :" + e.getMessage());
            setErrorResponse(response,e.getMessage());
        }


    }

    private void setErrorResponse(HttpServletResponse response, String message) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        Response result = new Response(message);
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
        String[] excludePath = {
            "/social/login",
            "/social/token", // 토큰 만료시 RefreshToken 요청은 AccessToken 검증 제외
        };
        String path = request.getRequestURI();
        return Arrays.stream(excludePath).anyMatch(path::startsWith);
    }
}
