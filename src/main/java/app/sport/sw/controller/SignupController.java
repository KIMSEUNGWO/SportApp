package app.sport.sw.controller;

import app.sport.sw.dto.Response;
import app.sport.sw.dto.ResponseData;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.dto.user.RegisterRequest;
import app.sport.sw.service.SignupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignupController {

    private final SignupService signupService;

    @PostMapping("/register")
    public ResponseEntity<Response> register(
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @RequestBody RegisterRequest registerRequest) {
        System.out.println("registerRequest = " + registerRequest);
        signupService.insertExtraData(userDetails.getUser().getId(), registerRequest);
        return ResponseEntity.ok(new Response("OK"));
    }

    @DeleteMapping("/register/clear")
    public ResponseEntity<Response> registerClear(@AuthenticationPrincipal CustomUserDetails userDetails) {
        boolean result = signupService.registerClear(userDetails.getUser().getId());
        System.out.println("result = " + result);
        return ResponseEntity.ok(new ResponseData<>("OK", result));
    }
}
