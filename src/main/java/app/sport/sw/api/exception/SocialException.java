package app.sport.sw.api.exception;

import app.sport.sw.response.SocialError;
import lombok.Getter;

@Getter
public class SocialException extends RuntimeException {

    private final SocialError socialError;

    public SocialException(SocialError socialError) {
        this.socialError = socialError;
    }
}
