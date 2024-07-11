package app.sport.sw.controller;

import app.sport.sw.api.LineAPI;
import app.sport.sw.api.LineProfile;
import app.sport.sw.component.AccessTokenVerifer;
import app.sport.sw.component.JwtUtil;
import app.sport.sw.component.SecurityUtil;
import app.sport.sw.domain.user.User;
import app.sport.sw.dto.Response;
import app.sport.sw.dto.ResponseData;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.dto.user.ResponseToken;
import app.sport.sw.dto.user.SocialLoginDto;
import app.sport.sw.service.SignupService;
import app.sport.sw.service.SocialService;
import app.sport.sw.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/social")
public class SocialController {

    private final SocialService socialService;
    private final SignupService signupService;

    private final AccessTokenVerifer tokenVerifer;
    private final SecurityUtil securityUtil;
    private final JwtUtil jwtUtil;
    private final UserService userService;


    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody @Validated SocialLoginDto loginDto) {

        // Line API 에서 사용자 정보 검증 및 가져오기
        LineProfile profile = tokenVerifer.getLineProfile(loginDto.getAccessToken());

        // 로그인 또는 회원가입
        Optional<User> findUser = socialService.socialLogin(loginDto);

        if (findUser.isEmpty()) {
            User register = signupService.register(loginDto, profile);
            securityUtil.saveUserInSecurityContext(loginDto);
            ResponseToken responseToken = jwtUtil.initToken(register);
            return ResponseEntity.ok(new ResponseData<>("REGISTER", responseToken));
        }

        User user = findUser.get();

        securityUtil.saveUserInSecurityContext(loginDto);

        ResponseToken responseToken = jwtUtil.initToken(user);

        return ResponseEntity.ok(new ResponseData<>("OK", responseToken));
    }

    @PostMapping("/token")
    public ResponseEntity<ResponseToken> refreshingAccessToken(HttpServletRequest request) {
        String refreshToken = jwtUtil.extractTokenFromHeader(request);
        jwtUtil.validateRefreshToken(refreshToken);

        User user = socialService.getUserInfoByUsingRefreshToken(refreshToken);
        ResponseToken responseToken = jwtUtil.refreshingAccessToken(user, refreshToken);
        return ResponseEntity.ok(responseToken);
    }

}
