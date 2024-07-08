package app.sport.sw.exception;

public class TokenException extends RuntimeException {

    public TokenException(TokenError tokenError) {
        super(tokenError.name());
    }
}
