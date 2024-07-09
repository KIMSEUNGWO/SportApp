package app.sport.sw.service;

import app.sport.sw.api.LineProfile;
import app.sport.sw.component.JwtUtil;
import app.sport.sw.domain.user.User;
import app.sport.sw.dto.user.ResponseToken;
import app.sport.sw.dto.user.SocialLoginDto;
import app.sport.sw.component.SecurityUtil;
import app.sport.sw.exception.TokenError;
import app.sport.sw.exception.TokenException;
import app.sport.sw.repository.SocialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SocialService {

    private final SignupService signupService;

    private final SocialRepository socialRepository;
    private final JwtUtil jwtUtil;


    public User socialLogin(SocialLoginDto loginDto, LineProfile profile) {

        return socialRepository
            .findBySocialIdAndProvider(loginDto.getSocialId(), loginDto.getProvider())
            .orElseGet(() -> signupService.register(loginDto, profile));
    }

    public User getUserInfoByUsingRefreshToken(String refreshToken) {
        return socialRepository.findByRefreshToken(refreshToken)
            .orElseThrow(() -> new TokenException(TokenError.REFRESH_TOKEN_EXPIRED));
    }
}
