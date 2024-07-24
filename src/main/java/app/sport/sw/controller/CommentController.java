package app.sport.sw.controller;

import app.sport.sw.dto.Response;
import app.sport.sw.dto.ResponseData;
import app.sport.sw.dto.comment.RequestCreateComment;
import app.sport.sw.dto.comment.RequestEditComment;
import app.sport.sw.dto.comment.ResponseComment;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.response.SuccessCode;
import app.sport.sw.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/club/{clubId}/board/{boardId}/comment")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<Response> getComments(@PathVariable("boardId") long boardId) {
        List<ResponseComment> comments = commentService.findByBoardId(boardId);
        return ResponseEntity.ok(new ResponseData<>(SuccessCode.OK, comments));
    }

    @PostMapping
    public ResponseEntity<Response> create(@PathVariable("boardId") long boardId,
                                           @AuthenticationPrincipal CustomUserDetails userDetails,
                                           @RequestBody RequestCreateComment createComment) {
        commentService.createComment(boardId, userDetails.getUser().getId(), createComment);
        return ResponseEntity.ok(new Response(SuccessCode.OK));
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<Response> edit(@PathVariable("commentId") long commentId,
                                         @RequestBody RequestEditComment editComment) {
        commentService.editComment(commentId, editComment);
        return ResponseEntity.ok(new Response(SuccessCode.OK));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Response> delete(@PathVariable("commentId") long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok(new Response(SuccessCode.OK));
    }
}
