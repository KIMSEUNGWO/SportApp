package app.sport.sw.dto.comment;

import app.sport.sw.dto.common.UserSimp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class ResponseComment {

    private final Long parentCommentId;
    private final long commentId;
    private final String content;
    private final LocalDateTime createDate;
    private final boolean isUpdate;

    private final UserSimp user;
}
