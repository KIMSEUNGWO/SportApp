package app.sport.sw.mvc.user;

import app.sport.sw.component.JwtUtil;
import app.sport.sw.domain.user.User;
import app.sport.sw.dto.user.ResponseToken;
import app.sport.sw.dto.user.SocialLoginDto;
import app.sport.sw.mvc.security.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final SignupService signupService;

    private final UserRepository userRepository;
    private final SecurityUtil securityUtil;
    private final JwtUtil jwtUtil;


    public ResponseToken socialLogin(SocialLoginDto loginDto) {
        User user = userRepository
            .findBySocialIdAndProvider(loginDto.getSocialId(), loginDto.getProvider())
            .orElseGet(() -> signupService.register(loginDto));

        /*
            1. 로그인한 경우 -> LINE 에서 새로발급받은 AccessToken 로 변경
            2. 회원가입한 경우 -> 변함없음
         */
        user.setAccessToken(loginDto.getAccessToken());

        securityUtil.saveUserInSecurityContext(user);

//        jwtUtil.initToken(user);

        return new ResponseToken(user.getUserSocial());
    }

//    public ResponseToken refreshingAccessToken(HttpServletRequest request) {
//        String refreshToken = jwtUtil.extractTokenFromHeader(request);
//
//        jwtUtil.validateRefreshToken(refreshToken);
//
//        Optional<User> findUser = userRepository.findByRefreshToken(refreshToken);
//        return jwtUtil.refreshingAccessToken(findUser.get(), refreshToken);
//    }
}
