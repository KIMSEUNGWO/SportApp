package app.sport.sw.api.exception;

public class SocialException extends RuntimeException {

    public SocialException(SocialError socialError) {
        super(socialError.name());
    }
}
