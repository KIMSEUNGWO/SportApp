package app.sport.sw.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClubExceptionHandler {

    @ExceptionHandler(ClubException.class)
    public ResponseEntity<ClubError> handleClubException(ClubException e) {
        return ResponseEntity.badRequest().body(e.getError());
    }
}
