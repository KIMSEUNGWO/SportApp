package app.sport.sw.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AccessController {

    @GetMapping("/accessToken")
    public ResponseEntity<String> checkAccessToken() {
        System.out.println("MainController.checkAccessToken");
        return ResponseEntity.ok("ok");
    }

}
