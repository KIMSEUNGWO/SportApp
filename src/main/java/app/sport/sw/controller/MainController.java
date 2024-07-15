package app.sport.sw.controller;

import app.sport.sw.dto.club.RecentlyViewClub;
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
    public ResponseEntity<List<RecentlyViewClub>> main(@RequestParam("clubs") List<Long> clubIds) {

        List<RecentlyViewClub> clubList = clubService.findByClubs(clubIds);
        return ResponseEntity.ok(clubList);
    }
}
