package app.sport.sw.controller;

import app.sport.sw.Const;
import app.sport.sw.api.LineAPI;
import app.sport.sw.component.AccessTokenVerifer;
import app.sport.sw.component.JwtUtil;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.dto.user.ResponseProfile;
import app.sport.sw.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;


    @GetMapping("/profile")
    public ResponseEntity<ResponseProfile> getProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {

        ResponseProfile profile = userService.getUserProfile(userDetails.getUser().getId());

        System.out.println("profile = " + profile);
        return ResponseEntity.ok(profile);
    }

}
