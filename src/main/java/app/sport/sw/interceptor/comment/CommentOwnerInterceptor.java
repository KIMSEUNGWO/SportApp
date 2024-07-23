package app.sport.sw.interceptor.comment;

import app.sport.sw.domain.user.User;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.interceptor.InterceptorPathHelper;
import app.sport.sw.repository.CommentRepository;
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

        return true;
    }
}
