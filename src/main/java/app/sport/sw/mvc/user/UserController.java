package app.sport.sw.mvc.user;

import app.sport.sw.dto.user.ResponseToken;
import app.sport.sw.dto.user.SocialLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/social")
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ResponseToken> login(@RequestBody @Validated SocialLoginDto loginDto) {
        return ResponseEntity.ok(userService.socialLogin(loginDto));
    }

//    @PostMapping("/token")
//    public ResponseEntity<ResponseToken> refreshingAccessToken(HttpServletRequest request) {
//        return ResponseEntity.ok(userService.refreshingAccessToken(request));
//    }
}
