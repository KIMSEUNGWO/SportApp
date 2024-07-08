package app.sport.sw.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SocialError {

    INVALID_REQUEST,
    INVALID_CLIENT_ID,

}
