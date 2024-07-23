package app.sport.sw.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ResponseComment {

    private final Long parentCommentId;
    private final long commentId;
    private final String content;
    private final LocalDateTime createDate;

    private final long userId;
    private final String nickname;
    private final String profile;
    private final List<ResponseComment> replyComments;
}
