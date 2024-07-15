package app.sport.sw.controller;

import app.sport.sw.component.BindingField;
import app.sport.sw.dto.Response;
import app.sport.sw.dto.ResponseData;
import app.sport.sw.dto.club.ClubCreateRequest;
import app.sport.sw.dto.club.DefaultClubInfo;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.response.ClubError;
import app.sport.sw.response.SuccessCode;
import app.sport.sw.service.ClubService;
import jakarta.servlet.http.HttpServletRequest;
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

        clubService.createClub(userDetails, clubCreateRequest);
        return ResponseEntity.ok(new Response(SuccessCode.OK));
    }


    @GetMapping("/{clubId}")
    public ResponseEntity<DefaultClubInfo> defaultGroupData(@PathVariable("clubId") long clubId,
                                                            @AuthenticationPrincipal CustomUserDetails userDetails) {

        return ResponseEntity.ok(clubService.getClubData(clubId, userDetails));
    }
}
