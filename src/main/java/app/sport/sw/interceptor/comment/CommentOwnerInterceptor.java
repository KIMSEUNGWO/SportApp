package app.sport.sw.interceptor.comment;

import app.sport.sw.domain.group.UserClub;
import app.sport.sw.domain.group.board.Comment;
import app.sport.sw.domain.user.User;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.enums.Authority;
import app.sport.sw.enums.Role;
import app.sport.sw.exception.ClubException;
import app.sport.sw.exception.CommentException;
import app.sport.sw.interceptor.InterceptorPathHelper;
import app.sport.sw.repository.ClubRepository;
import app.sport.sw.repository.CommentRepository;
import app.sport.sw.repository.UserClubRepository;
import app.sport.sw.response.ClubError;
import app.sport.sw.response.CommentError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class CommentOwnerInterceptor implements HandlerInterceptor {

    private final UserClubRepository userClubRepository;
    private final CommentRepository commentRepository;
    private final InterceptorPathHelper pathHelper;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("CommentOwnerInterceptor 진입");
        String method = request.getMethod();

        long commentId = pathHelper.getCommentId(request);
        log.info("commentId: {}", commentId);

        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = principal.getUser();

        Comment comment = commentRepository.findById(commentId);

        // 운영자는 모든 권한에 접근이 가능하다.
        if (user.getRole() == Role.ADMIN) return true;
        User commentUser = comment.getUser();

        // 자신의 댓글은 수정 및 삭제 할 수 있다.
        if (commentUser.getId() == user.getId()) {
            return true;
        }

        // 모임장 또는 매니저는 댓글을 삭제할 수 있다.
        long clubId = pathHelper.getClubId(request);
        Authority authority = userClubRepository.findByClubIdAndUserId(clubId, principal)
            .orElseThrow(() -> new ClubException(ClubError.CLUB_NOT_JOIN_USER))
            .getAuthority();
        if (authority == Authority.OWNER || authority == Authority.MANAGER) {
            return true;
        }

        throw new CommentException(CommentError.COMMENT_NOT_OWNER);
    }
}
