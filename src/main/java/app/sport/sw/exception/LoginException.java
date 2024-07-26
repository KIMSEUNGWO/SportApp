package app.sport.sw.exception;

import app.sport.sw.response.UserCode;
import lombok.Getter;

@Getter
public class LoginException extends CustomRuntimeException {

    public LoginException(UserCode userCode) {
        super(userCode);
    }
}
