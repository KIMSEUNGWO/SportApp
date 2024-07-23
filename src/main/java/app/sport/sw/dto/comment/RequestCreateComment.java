package app.sport.sw.dto.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestCreateComment {

    private Long parentCommentId;
    private String content;
}
