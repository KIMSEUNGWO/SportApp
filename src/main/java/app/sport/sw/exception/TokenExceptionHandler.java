package app.sport.sw.exception;

import app.sport.sw.api.exception.SocialException;
import app.sport.sw.dto.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TokenExceptionHandler {

    @ExceptionHandler(SocialException.class)
    public ResponseEntity<Response> handleSocialException(SocialException e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body(new Response(e.getMessage()));
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<Response> handleTokenException(TokenException e) {
        e.printStackTrace();
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.FORBIDDEN);
    }
}
