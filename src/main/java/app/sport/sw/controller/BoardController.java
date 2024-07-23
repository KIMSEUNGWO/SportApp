package app.sport.sw.controller;

import app.sport.sw.component.BindingField;
import app.sport.sw.dto.Response;
import app.sport.sw.dto.ResponseData;
import app.sport.sw.dto.board.BoardCreateRequest;
import app.sport.sw.dto.board.ResponseBoard;
import app.sport.sw.dto.board.ResponseBoardDetail;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.enums.group.BoardType;
import app.sport.sw.response.ClubError;
import app.sport.sw.response.SuccessCode;
import app.sport.sw.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/club/{clubId}/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/create")
    public ResponseEntity<Response> noticeCreate(@PathVariable("clubId") long clubId,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Validated @ModelAttribute BoardCreateRequest createRequest,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            List<String> fieldNames = BindingField.getFieldNames(bindingResult);
            return ResponseEntity.badRequest().body(new ResponseData<>(ClubError.INVALID_DATA, fieldNames));
        }

        boardService.createBoard(clubId, userDetails, createRequest);
        return ResponseEntity.ok(new Response(SuccessCode.OK));
    }

    @GetMapping
    public ResponseEntity<Response> getBoardList(@PathVariable("clubId") long clubId,
                                                 @RequestParam(value = "boardType", required = false) String boardType,
                                                 Pageable pageable) {
        List<ResponseBoard> boardList = boardService.getBoardList(clubId, BoardType.fromJson(boardType), pageable);
        return ResponseEntity.ok(new ResponseData<>(SuccessCode.OK, boardList));
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<Response> getBoardDetail(@PathVariable("boardId") long boardId) {
        ResponseBoardDetail boardDetail = boardService.getBoardDetail(boardId);
        return ResponseEntity.ok(new ResponseData<>(SuccessCode.OK, boardDetail));
    }
}
