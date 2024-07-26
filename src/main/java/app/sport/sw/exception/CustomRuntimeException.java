package app.sport.sw.exception;

import app.sport.sw.response.ResponseCode;
import lombok.Getter;

@Getter
public class CustomRuntimeException extends RuntimeException {

    private final ResponseCode responseCode;

    public CustomRuntimeException(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }
}
