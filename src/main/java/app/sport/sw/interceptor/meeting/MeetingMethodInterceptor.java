package app.sport.sw.interceptor.meeting;

import app.sport.sw.interceptor.InterceptorPathHelper;
import app.sport.sw.jparepository.JpaMeetingRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class MeetingMethodInterceptor implements HandlerInterceptor {

    private final JpaMeetingRepository jpaMeetingRepository;
    private final InterceptorPathHelper pathHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("MeetingMethodInterceptor 진입");
        String method = request.getMethod();


        return true;
    }
}
