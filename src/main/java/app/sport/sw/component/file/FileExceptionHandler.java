package app.sport.sw.component.file;

import app.sport.sw.dto.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

@Slf4j
@RestControllerAdvice
public class FileExceptionHandler {

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<Response> maxUploadSizeExceeded(MaxUploadSizeExceededException e) {
        log.error("MaxUploadSizeExceededException 예외 발생 !!");
        e.printStackTrace();
        return ResponseEntity.badRequest().body(new Response("MAX_UPLOAD_SIZE_EXCEEDED"));
    }
}
