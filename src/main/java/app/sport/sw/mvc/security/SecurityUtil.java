package app.sport.sw.mvc.security;

import app.sport.sw.component.JwtUtil;
import app.sport.sw.domain.user.User;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.enums.SocialProvider;
import app.sport.sw.exception.TokenError;
import app.sport.sw.exception.TokenException;
import app.sport.sw.mvc.user.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityUtil {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public void saveUserInSecurityContext(User user) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(getAuthentication(user));
        SecurityContextHolder.setContext(context);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(User user) {
        UserDetails userDetails = new CustomUserDetails(user);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }


    public void saveUserInSecurityContext(String accessToken) {
        String socialId = jwtUtil.extractClaim(accessToken,  Claims::getSubject);
        String socialProvider = jwtUtil.extractClaim(accessToken, Claims::getIssuer);
        saveUserInSecurityContext(socialId, socialProvider);
    }

    private void saveUserInSecurityContext(String socialId, String socialProvider) {
        SocialProvider provider = SocialProvider.from(socialProvider);
        if (socialId == null || provider == null) throw new TokenException(TokenError.TOKEN_EXPIRED);

        UserDetails userDetails = loadUserBySocialIdAndSocialProvider(socialId, provider);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    private UserDetails loadUserBySocialIdAndSocialProvider(String socialId, SocialProvider provider) {
        return userRepository.findBySocialIdAndProvider(socialId, provider)
            .map(CustomUserDetails::new)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    }

}
