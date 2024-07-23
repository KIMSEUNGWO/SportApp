package app.sport.sw.controller;

import app.sport.sw.dto.Response;
import app.sport.sw.dto.comment.RequestCreateComment;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.response.SuccessCode;
import app.sport.sw.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/club/{clubId}/board/{boardId}/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Response> create(@PathVariable("boardId") long boardId,
                                           @AuthenticationPrincipal CustomUserDetails userDetails,
                                           @RequestBody RequestCreateComment createComment) {
        commentService.createComment(boardId, userDetails.getUser().getId(), createComment);
        return ResponseEntity.ok(new Response(SuccessCode.OK));
    }
}
