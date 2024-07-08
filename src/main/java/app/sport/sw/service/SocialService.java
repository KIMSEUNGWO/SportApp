package app.sport.sw.service;

import app.sport.sw.api.LineProfile;
import app.sport.sw.component.JwtUtil;
import app.sport.sw.domain.user.User;
import app.sport.sw.dto.user.ResponseToken;
import app.sport.sw.dto.user.SocialLoginDto;
import app.sport.sw.component.SecurityUtil;
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
    private final SecurityUtil securityUtil;
    private final JwtUtil jwtUtil;


    public ResponseToken socialLogin(SocialLoginDto loginDto, LineProfile profile) {
        User user = socialRepository
            .findBySocialIdAndProvider(loginDto.getSocialId(), loginDto.getProvider())
            .orElseGet(() -> signupService.register(loginDto, profile));
        /*
            1. 로그인한 경우 -> LINE 에서 새로발급받은 AccessToken 로 변경
            2. 회원가입한 경우 -> 변함없음
         */
        user.setAccessToken(loginDto.getAccessToken());

        securityUtil.saveUserInSecurityContext(user);

        return new ResponseToken(user.getUserSocial());
    }

}
