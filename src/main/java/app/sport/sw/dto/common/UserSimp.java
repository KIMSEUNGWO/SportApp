package app.sport.sw.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserSimp {

    private final long userId;
    private final String thumbnailUser;
    private final String nickname;
}
