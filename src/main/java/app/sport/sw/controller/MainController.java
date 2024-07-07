package app.sport.sw.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {


    @GetMapping("/")
    public String main(@RequestParam("code") String code, @RequestParam("status") String status) {
        return "index";
    }
}
