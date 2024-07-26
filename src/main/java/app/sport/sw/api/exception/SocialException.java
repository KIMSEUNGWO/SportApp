package app.sport.sw.api.exception;

import app.sport.sw.exception.CustomRuntimeException;
import app.sport.sw.response.SocialError;
import lombok.Getter;

@Getter
public class SocialException extends CustomRuntimeException {

    public SocialException(SocialError socialError) {
        super(socialError);
    }
}
