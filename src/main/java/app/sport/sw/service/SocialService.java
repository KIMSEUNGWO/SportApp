package app.sport.sw.service;

import app.sport.sw.api.LineProfile;
import app.sport.sw.component.JwtUtil;
import app.sport.sw.domain.user.User;
import app.sport.sw.dto.user.SocialLoginDto;
import app.sport.sw.exception.TokenError;
import app.sport.sw.exception.TokenException;
import app.sport.sw.repository.SocialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class SocialService {

    private final SocialRepository socialRepository;
    private final JwtUtil jwtUtil;


    public Optional<User> socialLogin(SocialLoginDto loginDto) {
        return socialRepository
            .findBySocialIdAndProvider(loginDto.getSocialId(), loginDto.getProvider());
    }

    public User getUserInfoByUsingRefreshToken(String refreshToken) {
        return socialRepository.findByRefreshToken(refreshToken)
            .orElseThrow(() -> new TokenException(TokenError.REFRESH_TOKEN_EXPIRED));
    }
}
