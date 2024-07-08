package app.sport.sw.controller;

import app.sport.sw.Const;
import app.sport.sw.api.LineAPI;
import app.sport.sw.component.AccessTokenVerifer;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.dto.user.ResponseProfile;
import app.sport.sw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final AccessTokenVerifer tokenVerifer;
    private final UserService userService;


    @GetMapping("/profile")
    public ResponseEntity<ResponseProfile> getProfile(
        @RequestHeader(Const.AUTHORIZATION) String accessTokenHeader) {

        tokenVerifer.verifyAccessToken(accessTokenHeader);
        String accessToken = tokenVerifer.parse(accessTokenHeader);
        ResponseProfile profile = userService.getUserProfile(accessToken);

        System.out.println("profile = " + profile);
        return ResponseEntity.ok(profile);
    }

}
