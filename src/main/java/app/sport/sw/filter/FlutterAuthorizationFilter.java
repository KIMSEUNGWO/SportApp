package app.sport.sw.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
@Slf4j
public class FlutterAuthorizationFilter extends OncePerRequestFilter {

    @Value("${sport.api-key}")
    private String API_KEY;
    private final String HEADER_NAME = "Sport-Authorization";

    private final List<String> excludePath = List.of(
        "/favicon.ico",
        "/images/"
    );


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("FlutterAuthorizationFilter 시작 : 요청 URI = " + request.getRequestURI());
        String apiKey = request.getHeader(HEADER_NAME);

        if (!API_KEY.equals(apiKey)) {
            log.error("외부 요청이 발생했습니다.");
            throw new RuntimeException();
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        return excludePath.stream().anyMatch(path::startsWith);
    }
}
