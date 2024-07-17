package app.sport.sw.exception;

import app.sport.sw.response.ClubError;
import lombok.Getter;

@Getter
public class ClubException extends RuntimeException {

    private final ClubError error;

    public ClubException(ClubError error) {
        this.error = error;
    }
}
