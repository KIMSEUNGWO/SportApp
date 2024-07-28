package app.sport.sw.controller;

import app.sport.sw.dto.Response;
import app.sport.sw.dto.comment.RequestCreateComment;
import app.sport.sw.dto.comment.RequestEditComment;
import app.sport.sw.dto.comment.ResponseComment;
import app.sport.sw.dto.comment.ResponseTotalCount;
import app.sport.sw.dto.user.CustomUserDetails;
import app.sport.sw.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public ResponseEntity<Response> getComments(@PathVariable("boardId") long boardId,
                                                @RequestParam(name = "start", defaultValue = "0") int start,
                                                @RequestParam(name = "size", defaultValue = "10") int size,
                                                @RequestParam(name = "reload", defaultValue = "false") boolean reload) {

        Pageable pageable = PageRequest.of(start / size, size);
        List<ResponseComment> comments = commentService.findByBoardId(boardId, pageable, start, reload);
        int totalCount = commentService.countByBoardId(boardId);
        return Response.ok(new ResponseTotalCount(totalCount, comments));
    }

    @PostMapping
    public ResponseEntity<Response> create(@PathVariable("boardId") long boardId,
                                           @AuthenticationPrincipal CustomUserDetails userDetails,
                                           @RequestBody RequestCreateComment createComment) {
        commentService.createComment(boardId, userDetails.getUser().getId(), createComment);
        return Response.ok();
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<Response> edit(@PathVariable("commentId") long commentId,
                                         @RequestBody RequestEditComment editComment) {
        commentService.editComment(commentId, editComment);
        return Response.ok();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Response> delete(@PathVariable("commentId") long commentId) {
        commentService.deleteComment(commentId);
        return Response.ok();
    }
}
