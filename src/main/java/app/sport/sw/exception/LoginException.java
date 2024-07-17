package app.sport.sw.exception;

import app.sport.sw.response.UserCode;
import lombok.Getter;

@Getter
public class LoginException extends RuntimeException {

    final UserCode userCode;

    public LoginException(UserCode userCode) {
        this.userCode = userCode;
    }
}
