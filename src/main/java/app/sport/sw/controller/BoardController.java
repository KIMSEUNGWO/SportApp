package app.sport.sw.controller;

import app.sport.sw.component.file.FileService;
import app.sport.sw.domain.BaseEntityImage;
import app.sport.sw.dto.Response;
import app.sport.sw.dto.ResponseData;
import app.sport.sw.dto.board.BoardCreateRequest;
import app.sport.sw.dto.board.RequestBoardEdit;
import app.sport.sw.dto.board.ResponseBoard;
import app.sport.sw.dto.board.ResponseBoardDetail;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.enums.group.BoardType;
import app.sport.sw.exception.BindingException;
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
    private final FileService fileService;

    @PostMapping("/create")
    public ResponseEntity<Response> noticeCreate(@PathVariable("clubId") long clubId,
            @AuthenticationPrincipal CustomUserDetails userDetails,
            @Validated @ModelAttribute BoardCreateRequest createRequest,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) throw new BindingException(bindingResult);

        boardService.createBoard(clubId, userDetails, createRequest);
        return Response.ok();
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
        return Response.ok(boardDetail);
    }

    @PatchMapping("/{boardId}")
    public ResponseEntity<Response> editBoard(@PathVariable("boardId") long boardId,
                                              @ModelAttribute RequestBoardEdit requestBoardEdit) {
        boardService.editBoard(boardId, requestBoardEdit);
        return Response.ok();
    }

    @DeleteMapping("/{boardId}")
    public ResponseEntity<Response> deleteBoard(@PathVariable("boardId") long boardId) {
        List<? extends BaseEntityImage> baseEntityImages = boardService.deleteBoard(boardId);
        fileService.deleteImages(baseEntityImages);
        return Response.ok();
    }
}
