package app.sport.sw.interceptor.club;

import app.sport.sw.domain.group.UserClub;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.enums.Authority;
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
public class ClubMethodInterceptor implements HandlerInterceptor {

    private final JpaUserClubRepository jpaUserClubRepository;
    private final InterceptorPathHelper pathHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("ClubMethodInterceptor 진입");
        String method = request.getMethod();

        // 클럽 조회는 누구든지 가능
        if ("GET".equals(method)) {
            return true;
        }
        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal.getUser().getRole() == Role.ADMIN) return true;

        long clubId = pathHelper.getClubId(request);
        log.info("clubId: {}", clubId);

        if ("POST".equals(method)) {
            return true;
        }

        UserClub userClub = jpaUserClubRepository.findByClub_IdAndUser_Id(clubId, principal.getUser().getId())
            .orElseThrow(() -> new ClubException(ClubError.CLUB_NOT_JOIN_USER));

        if ("PATCH".equals(method) || "DELETE".equals(method)) {
            if (userClub.getAuthority() != Authority.OWNER) {
                throw new ClubException(ClubError.CLUB_NOT_OWNER);
            }
        }

        return true;
    }
}
