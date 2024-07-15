package app.sport.sw.exception.club;

import app.sport.sw.dto.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ClubExceptionHandler {

    @ExceptionHandler(ClubException.class)
    public ResponseEntity<Response> handleClubException(ClubException e) {
        return ResponseEntity.badRequest().body(new Response(e.getError()));
    }

}
