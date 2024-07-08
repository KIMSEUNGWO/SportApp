package app.sport.sw.controller;

import app.sport.sw.api.LineAPI;
import app.sport.sw.api.LineProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MainController {


    @GetMapping("/")
    public String main(@RequestParam("code") String code, @RequestParam("status") String status) {
        return "index";
    }

}
