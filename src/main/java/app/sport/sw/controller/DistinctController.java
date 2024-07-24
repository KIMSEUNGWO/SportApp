package app.sport.sw.controller;

import app.sport.sw.dto.Response;
import app.sport.sw.dto.ResponseData;
import app.sport.sw.response.SuccessCode;
import app.sport.sw.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DistinctController {

    private final UserService userService;

    @GetMapping("/distinct/nickname")
    public ResponseEntity<Response> distinctNickname(@RequestParam("nickname") String nickname) {
        boolean isDistinct = userService.distinctNickname(nickname);
        return ResponseEntity.ok(new ResponseData<>(SuccessCode.OK, isDistinct));
    }
}
