package app.sport.sw.controller;

import app.sport.sw.dto.Response;
import app.sport.sw.dto.ResponseData;
import app.sport.sw.dto.board.ResponseBoard;
import app.sport.sw.dto.club.DefaultClubInfo;
import app.sport.sw.dto.club.ResponseClubUser;
import app.sport.sw.dto.meeting.ResponseMeetingView;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.enums.group.BoardType;
import app.sport.sw.response.SuccessCode;
import app.sport.sw.service.BoardService;
import app.sport.sw.service.ClubService;
import app.sport.sw.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/public/club")
public class PublicDataController {

    private final ClubService clubService;
    private final BoardService boardService;
    private final MeetingService meetingService;

    @GetMapping("/{clubId}")
    public ResponseEntity<Response> defaultGroupData(@PathVariable("clubId") long clubId,
                                                     @AuthenticationPrincipal CustomUserDetails userDetails) {
        DefaultClubInfo clubData = clubService.getClubData(clubId, userDetails);
        return Response.ok(clubData);
    }

    @GetMapping("/{clubId}/users")
    public ResponseEntity<Response> getClubUsers(@PathVariable("clubId") long clubId) {

        List<ResponseClubUser> clubUsers = clubService.getClubUsers(clubId);
        return Response.ok(clubUsers);
    }

    @GetMapping("/{clubId}/board")
    public ResponseEntity<Response> getBoardList(@PathVariable("clubId") long clubId,
                                                 @RequestParam(value = "boardType", required = false) String boardType,
                                                 Pageable pageable) {
        List<ResponseBoard> boardList = boardService.getBoardList(clubId, BoardType.fromJson(boardType), pageable);
        return ResponseEntity.ok(new ResponseData<>(SuccessCode.OK, boardList));
    }

    // 일정 리스트 조회
    // 권한 필요없음. 비회원도 열람가능
    @GetMapping("/{clubId}/meeting")
    public ResponseEntity<Response> getMeetingList(@PathVariable("clubId") long clubId) {
        List<ResponseMeetingView> meetingList = meetingService.findAllByClubIdAsMeetingView(clubId);
        return Response.ok(meetingList);
    }
}
