package app.sport.sw.controller;

import app.sport.sw.dto.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class AccessController {

    @GetMapping("/accessToken")
    public ResponseEntity<Response> checkAccessToken() {
        System.out.println("MainController.checkAccessToken");
        return Response.ok();
    }

}
