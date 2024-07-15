package app.sport.sw.controller;

import app.sport.sw.dto.club.ResponseDefaultClubData;
import app.sport.sw.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {

    private final ClubService clubService;

    @GetMapping("/{clubId}")
    public ResponseEntity<ResponseDefaultClubData> defaultGroupData(@PathVariable long clubId) {
        return ResponseEntity.ok(clubService.getClubData(clubId));
    }
}
