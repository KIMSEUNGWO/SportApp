package app.sport.sw.interceptor;

import app.sport.sw.domain.group.UserClub;
import app.sport.sw.domain.group.board.Board;
import app.sport.sw.domain.user.User;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.enums.Authority;
import app.sport.sw.enums.Role;
import app.sport.sw.exception.board.BoardException;
import app.sport.sw.exception.club.ClubException;
import app.sport.sw.repository.BoardRepository;
import app.sport.sw.repository.UserClubRepository;
import app.sport.sw.response.BoardError;
import app.sport.sw.response.ClubError;
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
public class BoardOwnerInterceptor implements HandlerInterceptor {

    private final UserClubRepository userClubRepository;
    private final BoardRepository boardRepository;
    private final InterceptorPathHelper pathHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("BoardOwnerInterceptor 진입");
        String method = request.getMethod();

        long boardId = pathHelper.getBoardId(request);
        log.info("boardId: {}", boardId);

        CustomUserDetails principal = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = principal.getUser();

        Board board = boardRepository.findById(boardId);

        // 게시글 수정은 본인만 가능하다
        if ("PATCH".equalsIgnoreCase(method)) {
            if (board.getUser().getId() != user.getId()) throw new BoardException(BoardError.BOARD_NOT_OWNER);
            return true;
        }

        if (user.getRole() == Role.ADMIN) return true;

        if (board.getUser().getId() == user.getId()) {
            return true;
        }
        UserClub userClub = userClubRepository.findByClubIdAndUserId(board.getClub().getId(), principal)
                .orElseThrow(() -> new ClubException(ClubError.CLUB_NOT_JOIN_USER));

        if (userClub.getAuthority() == Authority.OWNER || userClub.getAuthority() == Authority.MANAGER) {
            return true;
        } else {
            throw new BoardException(BoardError.BOARD_NOT_OWNER);
        }
    }
}
