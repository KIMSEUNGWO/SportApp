package app.sport.sw.controller;

import app.sport.sw.dto.Response;
import app.sport.sw.dto.club.ClubListView;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.dto.user.EditProfileRequest;
import app.sport.sw.dto.user.ResponseProfile;
import app.sport.sw.service.ClubService;
import app.sport.sw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final ClubService clubService;

    @GetMapping("/profile")
    public ResponseEntity<Response> getProfile(@AuthenticationPrincipal CustomUserDetails userDetails) {
        ResponseProfile profile = userService.getUserProfile(userDetails.getUser().getId());
        return Response.ok(profile);
    }

    @PostMapping("/edit")
    public ResponseEntity<Response> editProfile(@AuthenticationPrincipal CustomUserDetails userDetails,
                                              @ModelAttribute EditProfileRequest editProfileRequest) {
        userService.editUserProfile(userDetails.getUser().getId(), editProfileRequest);
        return Response.ok();
    }

    @GetMapping("/clubs")
    public ResponseEntity<Response> getMyClubs(@AuthenticationPrincipal CustomUserDetails userDetails) {
        List<ClubListView> myClubs = clubService.getMyClubs(userDetails.getUser().getId());
        return Response.ok(myClubs);

    }

}
