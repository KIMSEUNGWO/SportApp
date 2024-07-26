package app.sport.sw.exceptionhandler;

import app.sport.sw.api.exception.SocialException;
import app.sport.sw.component.file.FileUploadException;
import app.sport.sw.dto.Response;
import app.sport.sw.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BoardException.class)
    public ResponseEntity<Response> boardException(BoardException e) {
        e.printStackTrace();
        log.error("BoardException 발생 !!! : {}", e.getResponseCode());
        return ResponseEntity.badRequest().body(new Response(e.getResponseCode()));
    }

    @ExceptionHandler(ClubException.class)
    public ResponseEntity<Response> handleClubException(ClubException e) {
        e.printStackTrace();
        log.error("ClubException 발생 !!! : {}", e.getResponseCode());
        return ResponseEntity.badRequest().body(new Response(e.getResponseCode()));
    }

    @ExceptionHandler(VIPException.class)
    public ResponseEntity<Response> vipException(VIPException e) {
        e.printStackTrace();
        log.error("VIPException 예외 발생 !!! : {}", e.getResponseCode());
        return ResponseEntity.badRequest().body(new Response(e.getResponseCode()));
    }

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<Response> loginException(LoginException e) {
        e.printStackTrace();
        log.error("LoginException 예외 발생 !!! : {}", e.getResponseCode());
        return ResponseEntity.badRequest().body(new Response(e.getResponseCode()));
    }

    @ExceptionHandler(SocialException.class)
    public ResponseEntity<Response> handleSocialException(SocialException e) {
        e.printStackTrace();
        log.error("SocialException 예외 발생 !!! : {}", e.getResponseCode());
        return ResponseEntity.badRequest().body(new Response(e.getResponseCode()));
    }

    @ExceptionHandler(TokenException.class)
    public ResponseEntity<Response> handleTokenException(TokenException e) {
        e.printStackTrace();
        log.error("Token Exception 발생 !!! : {}", e.getResponseCode());
        return ResponseEntity.badRequest().body(new Response(e.getResponseCode()));
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<Response> fileUploadException(FileUploadException e) {
        e.printStackTrace();
        log.error("FileUploadException 예외 발생 !!! : {}", e.getResponseCode());
        return ResponseEntity.badRequest().body(new Response(e.getResponseCode()));
    }

    @ExceptionHandler(CommentException.class)
    public ResponseEntity<Response> commentException(CommentException e) {
        e.printStackTrace();
        log.error("CommentException 예외 발생 !!! : {}", e.getResponseCode());
        return ResponseEntity.badRequest().body(new Response(e.getResponseCode()));
    }
}
