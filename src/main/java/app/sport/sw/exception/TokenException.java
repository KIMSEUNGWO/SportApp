package app.sport.sw.exception;

import app.sport.sw.response.TokenError;
import lombok.Getter;

@Getter
public class TokenException extends CustomRuntimeException {

    public TokenException(TokenError tokenError) {
        super(tokenError);
    }
}
