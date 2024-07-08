package app.sport.sw.controller;

import app.sport.sw.api.LineAPI;
import app.sport.sw.api.LineProfile;
import app.sport.sw.component.AccessTokenVerifer;
import app.sport.sw.dto.user.SocialLoginDto;
import app.sport.sw.service.SocialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/social")
public class SocialController {

    private final SocialService socialService;
    private final AccessTokenVerifer tokenVerifer;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Validated SocialLoginDto loginDto) {

        LineProfile profile = tokenVerifer.getLineProfile(loginDto.getAccessToken());

        socialService.socialLogin(loginDto, profile);

        return ResponseEntity.ok("ok");
    }

}
