package app.sport.sw.interceptor.meeting;

import app.sport.sw.exception.BoardException;
import app.sport.sw.exception.MeetingException;
import app.sport.sw.interceptor.InterceptorPathHelper;
import app.sport.sw.jparepository.JpaMeetingRepository;
import app.sport.sw.response.BoardError;
import app.sport.sw.response.MeetingError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class MeetingExistsInterceptor implements HandlerInterceptor {

    private final JpaMeetingRepository jpaMeetingRepository;
    private final InterceptorPathHelper pathHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("MeetingExistsInterceptor 진입");

        long meetingId = pathHelper.getMeetingId(request);
        log.info("meetingId: {}", meetingId);

        boolean exists = jpaMeetingRepository.existsById(meetingId);
        if (!exists) throw new MeetingException(MeetingError.MEETING_NOT_EXISTS);

        return true;
    }
}
