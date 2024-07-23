package app.sport.sw.wrappers;

import app.sport.sw.domain.group.board.Comment;
import app.sport.sw.dto.comment.ResponseComment;
import org.springframework.stereotype.Component;

@Component
public class CommentWrapper {


    public ResponseComment commentWrap(Comment comment) {
        return ResponseComment.builder()
            .parentCommentId(comment.getParentComment() != null ? comment.getParentComment().getId() : null)
            .commentId(comment.getId())
            .content(comment.getComment())
            .createDate(comment.getCreateDate())
            .userId(comment.getUser().getId())
            .nickname(comment.getUser().getNickName())
            .profile(comment.getUser().getThumbnail())
            .replyComments(comment.getReplyComments()
                .stream()
                .map(this::commentWrap)
                .toList())
            .build();
    }
}
