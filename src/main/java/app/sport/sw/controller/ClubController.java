package app.sport.sw.controller;

import app.sport.sw.dto.Response;
import app.sport.sw.dto.club.ClubCreateRequest;
import app.sport.sw.dto.club.ClubEditRequest;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.exception.BindingException;
import app.sport.sw.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {

    private final ClubService clubService;

    @PostMapping
    public ResponseEntity<Response> createClub(
                @AuthenticationPrincipal CustomUserDetails userDetails,
                @Validated @RequestBody ClubCreateRequest clubCreateRequest,
                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) throw new BindingException(bindingResult);

        long clubId = clubService.createClub(userDetails, clubCreateRequest);
        return Response.ok(clubId);
    }

    @PatchMapping("/{clubId}")
    public ResponseEntity<Response> editClub(
                @PathVariable("clubId") long clubId,
                @Validated @ModelAttribute ClubEditRequest clubEditRequest,
                 BindingResult bindingResult) {

        if (bindingResult.hasErrors()) throw new BindingException(bindingResult);

        clubService.editClub(clubId, clubEditRequest);
        return Response.ok();
    }

    @DeleteMapping("/{clubId}")
    public ResponseEntity<Response> deleteClub(@PathVariable("clubId") long clubId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        System.out.println("모임 삭제 로직임");
        return Response.ok();
    }

    @PostMapping("/{clubId}")
    public ResponseEntity<Response> joinClub(@PathVariable("clubId") long clubId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        clubService.joinClub(clubId, userDetails);
        return Response.ok(clubId);
    }

    @DeleteMapping("/{clubId}/exit")
    public ResponseEntity<Response> exitClub(@PathVariable("clubId") long clubId,
                                             @AuthenticationPrincipal CustomUserDetails userDetails) {
        clubService.exitClub(clubId, userDetails);
        return Response.ok();
    }

}
