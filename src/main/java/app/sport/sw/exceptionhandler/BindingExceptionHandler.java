package app.sport.sw.exceptionhandler;

import app.sport.sw.dto.Response;
import app.sport.sw.dto.ResponseData;
import app.sport.sw.exception.BindingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static app.sport.sw.response.ClubError.INVALID_DATA;

@Slf4j
@RestControllerAdvice
public class BindingExceptionHandler {

    @ExceptionHandler(BindingException.class)
    public ResponseEntity<Response> handleBindingException(BindingException e) {
        e.printStackTrace();
        log.error("BindingException 예외 발생 !!! : {}", INVALID_DATA);
        List<String> fieldNames = getFieldNames(e.getBindingResult());
        return ResponseEntity.badRequest().body(new ResponseData<>(INVALID_DATA, fieldNames));
    }

    private List<String> getFieldNames(BindingResult bindingResult) {
        return bindingResult
            .getFieldErrors()
            .stream()
            .map(FieldError::getField)
            .toList();
    }
}
