package app.sport.sw.dto.board;

import app.sport.sw.dto.common.UserSimp;
import app.sport.sw.enums.group.BoardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ResponseBoardDetail {

    private final long boardId;
    private final String title;
    private final String content;
    private final LocalDateTime createDate;
    private final BoardType boardType;
    private final List<ResponseBoardImage> images;
    private final boolean isUpdate;

    private final int likeCount;

    private final UserSimp user;
}
