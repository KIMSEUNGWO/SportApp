package app.sport.sw.exception;

import app.sport.sw.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(VIPException.class)
    public ResponseEntity<Response> vipException(VIPException e) {
      log.error("VIPException 예외 발생 !!!");
      return ResponseEntity.badRequest().body(new Response(e.getVipCode()));
    }
}
