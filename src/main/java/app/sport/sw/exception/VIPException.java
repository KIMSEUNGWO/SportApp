package app.sport.sw.exception;

import app.sport.sw.response.VIPCode;
import lombok.Getter;

@Getter
public class VIPException extends RuntimeException {

    private final VIPCode vipCode;

    public VIPException(VIPCode vipCode) {
        this.vipCode = vipCode;
    }
}
