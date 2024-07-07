package app.sport.sw.dto.user;

import app.sport.sw.domain.user.UserSocial;
import app.sport.sw.enums.SocialProvider;
import lombok.Getter;

@Getter
public class UserDto {

    private String socialId;
    private SocialProvider provider;

    public UserDto(UserSocial userSocial) {
        socialId = userSocial.getSocialId();
        provider = userSocial.getProvider();
    }
}
