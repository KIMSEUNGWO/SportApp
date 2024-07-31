package app.sport.sw.service;

import app.sport.sw.component.JwtUtil;
import app.sport.sw.component.SecurityUtil;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.exception.TokenException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublicServiceImpl implements PublicService {

    private final JwtUtil jwtUtil;
    private final SecurityUtil securityUtil;

    @Override
    public CustomUserDetails getSecurityContextUserDetails(HttpServletRequest request) {
        try {
            String accessToken = jwtUtil.extractTokenFromHeader(request);
            jwtUtil.validateAccessToken(accessToken);
            securityUtil.saveUserInSecurityContext(accessToken);
            return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (TokenException e) {
            return null;
        }
    }
}
