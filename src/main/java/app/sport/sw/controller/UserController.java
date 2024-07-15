package app.sport.sw.controller;

import app.sport.sw.dto.Response;
import app.sport.sw.dto.ResponseData;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.dto.user.EditProfileRequest;
import app.sport.sw.dto.user.ResponseProfile;
import app.sport.sw.response.SuccessCode;
import app.sport.sw.service.SignupService;
import app.sport.sw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")
    public ResponseEntity<Response> getProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        ResponseProfile profile = userService.getUserProfile(userDetails.getUser().getId());
        return ResponseEntity.ok(new ResponseData<>(SuccessCode.OK, profile));
    }

    @PostMapping("/edit")
    public ResponseEntity<Response> editProfile(@AuthenticationPrincipal CustomUserDetails userDetails,
                                              @ModelAttribute EditProfileRequest editProfileRequest) {
        userService.editUserProfile(userDetails.getUser().getId(), editProfileRequest);
        return ResponseEntity.ok(new Response(SuccessCode.OK));
    }

}
