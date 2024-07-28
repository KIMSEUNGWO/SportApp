package app.sport.sw.controller;

import app.sport.sw.api.LineProfile;
import app.sport.sw.component.AccessTokenVerifer;
import app.sport.sw.component.JwtUtil;
import app.sport.sw.component.SecurityUtil;
import app.sport.sw.domain.user.User;
import app.sport.sw.dto.Response;
import app.sport.sw.dto.user.RegisterRequest;
import app.sport.sw.dto.user.ResponseToken;
import app.sport.sw.exception.BindingException;
import app.sport.sw.service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignupController {

    private final SignupService signupService;
    private final AccessTokenVerifer tokenVerifer;
    private final SecurityUtil securityUtil;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@Validated @RequestBody RegisterRequest registerRequest,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) throw new BindingException(bindingResult);

        // Line API 에서 사용자 정보 검증 및 가져오기
        LineProfile profile = tokenVerifer.getLineProfile(registerRequest.getAccessToken());

        User register = signupService.register(registerRequest, profile);

        securityUtil.saveUserInSecurityContext(registerRequest);
        ResponseToken responseToken = jwtUtil.initToken(register);

        System.out.println("responseToken = " + responseToken);
        return Response.ok(responseToken);
    }
}
