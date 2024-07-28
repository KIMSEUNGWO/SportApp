package app.sport.sw.exception;

import lombok.Getter;
import org.springframework.validation.BindingResult;

@Getter
public class BindingException extends RuntimeException {

    private final BindingResult bindingResult;

    public BindingException(BindingResult bindingResult) {
        this.bindingResult = bindingResult;
    }
}
