package app.sport.sw.controller;

import app.sport.sw.dto.Response;
import app.sport.sw.dto.meeting.RequestCreateMeeting;
import app.sport.sw.dto.meeting.RequestEditMeeting;
import app.sport.sw.dto.meeting.ResponseMeetingView;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.exception.BindingException;
import app.sport.sw.service.MeetingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/club/{clubId}/meeting")
public class MeetingController {

    private final MeetingService meetingService;


    // 일정 생성
    // 매니저 이상 권한 필요
    @PostMapping
    public ResponseEntity<Response> createMeeting(@Validated @RequestBody RequestCreateMeeting createMeeting,
                                                  BindingResult bindingResult,
                                                  @PathVariable("clubId") long clubId,
                                                  @AuthenticationPrincipal CustomUserDetails userDetails) {

        if (bindingResult.hasErrors()) throw new BindingException(bindingResult);
        meetingService.createMeeting(createMeeting, clubId, userDetails.getUser().getId());
        return Response.ok();
    }

    // 일정 상세 정보 조회
    // 참여회원 이상 권한 필요
    @GetMapping("/{meetingId}")
    public ResponseEntity<Response> getMeetingDetail(@PathVariable("meetingId") long meetingId) {
        ResponseMeetingView meetingView = meetingService.findByMeetingIdAsMeetingView(meetingId);
        return Response.ok(meetingView);
    }

    // 일정 참여
    // 참여회원 이상 권한 필요
    @PostMapping("/{meetingId}")
    public ResponseEntity<Response> joinMeeting(@PathVariable("meetingId") long meetingId,
                                                @AuthenticationPrincipal CustomUserDetails userDetails) {
        meetingService.joinMeeting(meetingId, userDetails.getUser().getId());
        return Response.ok();
    }

    // 일정 수정
    // 본인만 수정 가능
    @PatchMapping("/{meetingId}")
    public ResponseEntity<Response> editMeeting(@PathVariable("meetingId") long meetingId,
                                                @ModelAttribute RequestEditMeeting editMeeting) {
        meetingService.editMeeting(meetingId, editMeeting);
        return Response.ok();
    }

    // 일정 삭제
    // 매니저 이상 권한 필요
    @DeleteMapping("/{meetingId}")
    public ResponseEntity<Response> deleteMeeting(@PathVariable("meetingId") long meetingId) {
        meetingService.deleteMeeting(meetingId);
        return Response.ok();
    }

    // 일정 나가기
    // 본인만 나가기 가능
    @DeleteMapping("/{meetingId}/exit")
    public ResponseEntity<Response> exitMeeting(@PathVariable("meetingId") long meetingId,
                                                  @AuthenticationPrincipal CustomUserDetails userDetails) {
        meetingService.exitMeeting(meetingId, userDetails.getUser().getId());
        return Response.ok();
    }
}
