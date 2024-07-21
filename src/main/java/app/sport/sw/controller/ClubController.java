package app.sport.sw.controller;

import app.sport.sw.component.BindingField;
import app.sport.sw.dto.Response;
import app.sport.sw.dto.ResponseData;
import app.sport.sw.dto.club.ClubCreateRequest;
import app.sport.sw.dto.club.ClubEditRequest;
import app.sport.sw.dto.club.ResponseClubUser;
import app.sport.sw.dto.club.DefaultClubInfo;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.response.ClubError;
import app.sport.sw.response.SuccessCode;
import app.sport.sw.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/club")
public class ClubController {

    private final ClubService clubService;

    @PostMapping("/create")
    public ResponseEntity<Response> createClub(
                @AuthenticationPrincipal CustomUserDetails userDetails,
                @Validated @RequestBody ClubCreateRequest clubCreateRequest,
                BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<String> fieldNames = BindingField.getFieldNames(bindingResult);
            return ResponseEntity.badRequest().body(new ResponseData<>(ClubError.INVALID_DATA, fieldNames));
        }

        long clubId = clubService.createClub(userDetails, clubCreateRequest);
        return ResponseEntity.ok(new ResponseData<>(SuccessCode.OK, clubId));
    }

    @PostMapping("/{clubId}/edit")
    public ResponseEntity<Response> editClub(
                @PathVariable("clubId") long clubId,
                @Validated @ModelAttribute ClubEditRequest clubEditRequest,
                 BindingResult bindingResult) {

        System.out.println("clubEditRequest = " + clubEditRequest);
        if (bindingResult.hasErrors()) {
            List<String> fieldNames = BindingField.getFieldNames(bindingResult);
            return ResponseEntity.badRequest().body(new ResponseData<>(ClubError.INVALID_DATA, fieldNames));
        }
        clubService.editClub(clubId, clubEditRequest);
        return ResponseEntity.ok(new Response(SuccessCode.OK));
    }

    @PostMapping("/{clubId}/join")
    public ResponseEntity<Response> joinClub(@PathVariable("clubId") long clubId, @AuthenticationPrincipal CustomUserDetails userDetails) {
        clubService.joinClub(clubId, userDetails);
        return ResponseEntity.ok(new ResponseData<>(SuccessCode.OK, clubId));
    }


    @GetMapping("/{clubId}")
    public ResponseEntity<Response> defaultGroupData(@PathVariable("clubId") long clubId,
                                                     @AuthenticationPrincipal CustomUserDetails userDetails) {
        DefaultClubInfo clubData = clubService.getClubData(clubId, userDetails);
        System.out.println("clubData = " + clubData);
        return ResponseEntity.ok(new ResponseData<>(SuccessCode.OK, clubData));
    }

    @GetMapping("/{clubId}/users")
    public ResponseEntity<Response> getClubUsers(@PathVariable("clubId") long clubId) {

        List<ResponseClubUser> clubUsers = clubService.getClubUsers(clubId);
        return ResponseEntity.ok(new ResponseData<>(SuccessCode.OK, clubUsers));
    }
}
