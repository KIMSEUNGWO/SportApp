package app.sport.sw.exception;

import app.sport.sw.response.VIPCode;
import lombok.Getter;

@Getter
public class VIPException extends CustomRuntimeException {

    public VIPException(VIPCode vipCode) {
        super(vipCode);
    }
}
