package app.sport.sw.interceptor.club;

import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.enums.Role;
import app.sport.sw.exception.ClubException;
import app.sport.sw.interceptor.InterceptorPathHelper;
import app.sport.sw.jparepository.JpaUserClubRepository;
import app.sport.sw.response.ClubError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClubJoinInterceptor implements HandlerInterceptor {

    private final JpaUserClubRepository jpaUserClubRepository;
    private final InterceptorPathHelper pathHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("ClubJoinInterceptor 진입");

        long clubId = pathHelper.getClubId(request);
        log.info("clubId: {}", clubId);

        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.getUser().getRole() == Role.ADMIN) return true;

        long userId = principal.getUser().getId();

        boolean exists = jpaUserClubRepository.existsByClub_IdAndUser_Id(clubId, userId);
        if (!exists) throw new ClubException(ClubError.CLUB_NOT_JOIN_USER);

        return true;
    }
}
