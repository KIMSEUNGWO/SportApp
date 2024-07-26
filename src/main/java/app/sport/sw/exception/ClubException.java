package app.sport.sw.exception;

import app.sport.sw.response.ClubError;
import lombok.Getter;

@Getter
public class ClubException extends CustomRuntimeException {

    public ClubException(ClubError error) {
        super(error);
    }
}
