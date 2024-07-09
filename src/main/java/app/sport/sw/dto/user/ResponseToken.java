package app.sport.sw.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseToken {

    private final String accessToken;
    private final String refreshToken;
}
