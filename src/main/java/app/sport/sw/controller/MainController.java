package app.sport.sw.controller;

import app.sport.sw.dto.Response;
import app.sport.sw.dto.ResponseData;
import app.sport.sw.dto.club.ClubListView;
import app.sport.sw.response.SuccessCode;
import app.sport.sw.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final ClubService clubService;

    @GetMapping("/")
    public ResponseEntity<Response> main(@RequestParam("clubs") List<Long> clubIds) {
        System.out.println("clubIds = " + clubIds);
        List<Long> subList = clubIds.subList(0, Math.min(10, clubIds.size()));
        List<ClubListView> clubList = clubService.findByClubs(subList);
        System.out.println("clubList = " + clubList);
        return ResponseEntity.ok(new ResponseData<>(SuccessCode.OK, clubList));
    }
}
