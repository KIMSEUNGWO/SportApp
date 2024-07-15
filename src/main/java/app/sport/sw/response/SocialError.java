package app.sport.sw.response;

import lombok.Getter;

@Getter
public enum SocialError implements ResponseCode {

    INVALID_REQUEST,
    INVALID_CLIENT_ID,
    ;

    @Override
    public String getResult() {
        return this.name();
    }
}
