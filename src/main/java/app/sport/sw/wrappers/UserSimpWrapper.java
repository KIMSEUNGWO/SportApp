package app.sport.sw.wrappers;

import app.sport.sw.domain.user.User;
import app.sport.sw.dto.common.UserSimp;
import org.springframework.stereotype.Component;

@Component
public class UserSimpWrapper {

    public UserSimp userSimpWrap(User user) {
        return UserSimp.builder()
            .userId(user.getId())
            .thumbnailUser(user.getThumbnail())
            .nickname(user.getNickName())
            .build();
    }
}
