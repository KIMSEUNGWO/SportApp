package app.sport.sw.dto.user;

import app.sport.sw.domain.user.UserSocial;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseToken {

    private String accessToken;

    public ResponseToken(UserSocial userSocial) {
        this.accessToken = userSocial.getAccessToken();
    }

    public ResponseToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
