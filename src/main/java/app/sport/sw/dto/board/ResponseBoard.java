package app.sport.sw.dto.board;

import app.sport.sw.enums.group.BoardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@ToString
public class ResponseBoard {

    private final long boardId;
    private final String title;
    private final String content;
    private final BoardType boardType;

    private final String thumbnailBoard;

    private final int likeCount;
    private final int commentCount;
    private final LocalDateTime createDate;

    private final long userId;
    private final String thumbnailUser;
    private final String nickname;
}
