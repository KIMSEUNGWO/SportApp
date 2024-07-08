package app.sport.sw.dto.group;

import app.sport.sw.enums.group.BoardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
public class BoardGroupInfo {

    private final BoardType boardType;
    private final String image;
    private final String title;
    private final LocalDateTime createDate;

    private final String userImage;
    private final String userName;

}
