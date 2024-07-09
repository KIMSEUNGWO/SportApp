package app.sport.sw.component;

import app.sport.sw.api.LineAPI;
import app.sport.sw.api.LineProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccessTokenVerifer {

    private final LineAPI lineAPI;

    public void verifyAccessToken(String accessToken) {
        lineAPI.accessTokenVerify(parse(accessToken));
    }

    public String parse(String accessTokenHeader) {
        return accessTokenHeader.startsWith("Bearer ")
            ? accessTokenHeader.substring(7)
            : accessTokenHeader;
    }

    public LineProfile getLineProfile(String accessToken) {
        verifyAccessToken(accessToken);
        return lineAPI.getLineProfile(accessToken);
    }
}
