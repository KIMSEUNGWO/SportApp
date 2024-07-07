package app.sport.sw.dto.user;

import app.sport.sw.domain.user.UserSocial;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ResponseToken {

    private String accessToken;
    private String refreshToken;

    public ResponseToken(UserSocial userSocial) {
        this.accessToken = userSocial.getAccessToken();
        this.refreshToken = userSocial.getRefreshToken();
    }

    public ResponseToken(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
