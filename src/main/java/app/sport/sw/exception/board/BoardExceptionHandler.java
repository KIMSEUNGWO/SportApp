package app.sport.sw.exception.board;

import app.sport.sw.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class BoardExceptionHandler {

    @ExceptionHandler(BoardException.class)
    public ResponseEntity<Response> boardException(BoardException e) {
        log.error("BoardException 발생 !!!");
        e.printStackTrace();
        return ResponseEntity.badRequest().body(new Response(e.getBoardError()));
    }
}
