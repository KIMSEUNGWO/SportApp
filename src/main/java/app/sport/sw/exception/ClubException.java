package app.sport.sw.exception;

import lombok.Getter;

@Getter
public class ClubException extends RuntimeException {

    private final ClubError error;

    public ClubException(ClubError error) {
        this.error = error;
    }
}
