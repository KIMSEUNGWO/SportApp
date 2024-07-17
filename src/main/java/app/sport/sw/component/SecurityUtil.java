package app.sport.sw.component;

import app.sport.sw.domain.user.User;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.dto.user.RegisterRequest;
import app.sport.sw.dto.user.SocialLoginDto;
import app.sport.sw.enums.SocialProvider;
import app.sport.sw.response.TokenError;
import app.sport.sw.exception.TokenException;
import app.sport.sw.repository.SocialRepository;
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
    private final SocialRepository socialRepository;

    public void saveUserInSecurityContext(SocialLoginDto loginDto) {
        saveUserInSecurityContext(loginDto.getSocialId(), loginDto.getProvider());
    }
    public void saveUserInSecurityContext(RegisterRequest registerDto) {
        saveUserInSecurityContext(registerDto.getSocialId(), registerDto.getProvider());
    }

    public void saveUserInSecurityContext(String accessToken) {
        String socialId = jwtUtil.extractClaim(accessToken,  Claims::getSubject);
        String socialProvider = jwtUtil.extractClaim(accessToken, Claims::getIssuer);
        saveUserInSecurityContext(socialId, SocialProvider.from(socialProvider));
    }

    private void saveUserInSecurityContext(String socialId, SocialProvider provider) {
        if (socialId == null || provider == null) throw new TokenException(TokenError.TOKEN_EXPIRED);

        UserDetails userDetails = loadUserBySocialIdAndSocialProvider(socialId, provider);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }

    private UserDetails loadUserBySocialIdAndSocialProvider(String socialId, SocialProvider provider) {
        return socialRepository.findBySocialIdAndProvider(socialId, provider)
            .map(CustomUserDetails::new)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    private UsernamePasswordAuthenticationToken getAuthentication(User user) {
        UserDetails userDetails = new CustomUserDetails(user);
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }



}
