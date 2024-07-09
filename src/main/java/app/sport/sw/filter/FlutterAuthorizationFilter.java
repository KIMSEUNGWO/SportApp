package app.sport.sw.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class FlutterAuthorizationFilter extends OncePerRequestFilter {

    @Value("${sport.api-key}")
    private String API_KEY;
    private final String HEADER_NAME = "Sport-Authorization";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("FlutterAuthorizationFilter 시작");

        String apiKey = request.getHeader(HEADER_NAME);

        if (!API_KEY.equals(apiKey)) {
            log.error("외부 요청이 발생했습니다.");
            throw new RuntimeException();
        }

        filterChain.doFilter(request, response);
    }
}
