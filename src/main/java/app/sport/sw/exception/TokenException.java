package app.sport.sw.exception;

import app.sport.sw.response.ResponseCode;
import lombok.Getter;

@Getter
public class TokenException extends RuntimeException {

    private ResponseCode responseCode;

    public TokenException(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }
}
