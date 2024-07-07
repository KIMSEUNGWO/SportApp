package app.sport.sw.exception;

public class TokenException extends RuntimeException {

    private final TokenError tokenError;

    public TokenException(TokenError tokenError) {
        this.tokenError = tokenError;
    }
}
