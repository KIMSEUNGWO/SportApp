package app.sport.sw.interceptor.meeting;

import app.sport.sw.domain.group.UserClub;
import app.sport.sw.domain.meeting.Meeting;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.enums.Authority;
import app.sport.sw.exception.ClubException;
import app.sport.sw.exception.MeetingException;
import app.sport.sw.interceptor.InterceptorPathHelper;
import app.sport.sw.jparepository.JpaMeetingUserRepository;
import app.sport.sw.jparepository.JpaUserClubRepository;
import app.sport.sw.repository.MeetingRepository;
import app.sport.sw.response.ClubError;
import app.sport.sw.response.MeetingError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.function.Supplier;

@Slf4j
@Component
@RequiredArgsConstructor
public class MeetingMethodInterceptor implements HandlerInterceptor {

    private final MeetingRepository meetingRepository;
    private final JpaUserClubRepository jpaUserClubRepository;
    private final JpaMeetingUserRepository jpaMeetingUserRepository;
    private final InterceptorPathHelper pathHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("MeetingMethodInterceptor 진입");
        String method = request.getMethod();

        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Supplier<UserClub> userClubSupplier = () -> {
            long clubId = pathHelper.getClubId(request);
            return jpaUserClubRepository.findByClub_IdAndUser_Id(clubId, principal.getUser().getId())
                .orElseThrow(() -> new ClubException(ClubError.CLUB_NOT_JOIN_USER));
        };

        String requestURI = request.getRequestURI();
        if ("POST".equalsIgnoreCase(method) && requestURI.matches("/club/[0-9]+/meeting")) {
            // 일정 생성 로직, 매니저 이상 권한 필요
            if (userClubSupplier.get().getAuthority() == Authority.USER) {
                throw new MeetingException(MeetingError.MEETING_UNAUTHORIZED);
            }
            return true;
        }

        if ("PATCH".equalsIgnoreCase(method)) {
            // 일정은 본인만 수정 가능
            long meetingId = pathHelper.getMeetingId(request);
            Meeting meeting = meetingRepository.findById(meetingId);
            if (meeting.getUser().getId() != principal.getUser().getId()) {
                throw new MeetingException(MeetingError.MEETING_UNAUTHORIZED);
            }
            return true;
        }

        if ("DELETE".equalsIgnoreCase(method)) {

            // 일정 나가기 로직 (일정에 참여중인 회원 가능)
            if (requestURI.matches("/club/[0-9]+/meeting/[0-9]+/exit")) {
                long meetingId = pathHelper.getMeetingId(request);
                boolean exists = jpaMeetingUserRepository.existsByMeeting_IdAndUser_Id(meetingId, principal.getUser().getId());
                if (!exists) {
                    throw new MeetingException(MeetingError.MEETING_NOT_JOIN_USER);
                }
                return true;
            }

            // 일정 삭제 로직 ( 매니저 이상 권한 필요)
            if (userClubSupplier.get().getAuthority() == Authority.USER) {
                throw new MeetingException(MeetingError.MEETING_UNAUTHORIZED);
            }
            return true;
        }

        return true;
    }
}
