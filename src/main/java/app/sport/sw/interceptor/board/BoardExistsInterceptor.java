package app.sport.sw.interceptor.board;

import app.sport.sw.exception.BoardException;
import app.sport.sw.interceptor.InterceptorPathHelper;
import app.sport.sw.jparepository.JpaBoardRepository;
import app.sport.sw.response.BoardError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
@RequiredArgsConstructor
public class BoardExistsInterceptor implements HandlerInterceptor {

    private final JpaBoardRepository jpaBoardRepository;
    private final InterceptorPathHelper pathHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        log.info("BoardExistsInterceptor 진입");

        long boardId = pathHelper.getBoardId(request);
        log.info("boardId: {}", boardId);

        boolean exists = jpaBoardRepository.existsById(boardId);
        if (!exists) throw new BoardException(BoardError.BOARD_NOT_EXISTS);

        return true;
    }
}
