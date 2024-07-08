package app.sport.sw.mvc.club;

import app.sport.sw.dto.group.DefaultGroupInfo;
import app.sport.sw.dto.group.ResponseDefaultGroupData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class ClubController {

    private final ClubService clubService;

    @GetMapping("/{clubId}")
    public ResponseEntity<ResponseDefaultGroupData> defaultGroupData(@PathVariable long clubId) {
        return ResponseEntity.ok(clubService.getClubData(clubId));
    }
}
