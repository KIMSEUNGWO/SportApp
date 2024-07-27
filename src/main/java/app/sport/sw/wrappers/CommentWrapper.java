package app.sport.sw.wrappers;

import app.sport.sw.domain.group.board.Comment;
import app.sport.sw.dto.comment.ResponseComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentWrapper {

    private final UserSimpWrapper userSimpWrapper;


    public ResponseComment commentWrap(Comment comment) {
        return ResponseComment.builder()
            .parentCommentId(comment.getParentComment() != null ? comment.getParentComment().getId() : null)
            .commentId(comment.getId())
            .content(comment.getComment())
            .createDate(comment.getCreateDate())
            .isUpdate(comment.isUpdate())
            .user(userSimpWrapper.userSimpWrap(comment.getUser()))
            .build();
    }
}
