package app.sport.sw.interceptor;

import app.sport.sw.exception.club.ClubException;
import app.sport.sw.jparepository.JpaClubRepository;
import app.sport.sw.response.ClubError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClubExistsInterceptor implements HandlerInterceptor {

    private final JpaClubRepository jpaClubRepository;
    private final InterceptorPathHelper pathHelper;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("ClubInterceptor 진입");

        long clubId = pathHelper.getClubId(request);
        log.info("clubId: {}", clubId);

        boolean exists = jpaClubRepository.existsById(clubId);
        if (!exists) throw new ClubException(ClubError.CLUB_NOT_EXISTS);

        return true;
    }

}
