package app.sport.sw.exception;

import app.sport.sw.response.TokenError;
import lombok.Getter;

@Getter
public class TokenException extends RuntimeException {

    private TokenError tokenError;

    public TokenException(TokenError tokenError) {
        this.tokenError = tokenError;
    }
}
