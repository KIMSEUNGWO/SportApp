package app.sport.sw.component;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

@Component
public abstract class BindingField {

    private BindingField() {}

    public static List<String> getFieldNames(BindingResult bindingResult) {
        return bindingResult
            .getFieldErrors()
            .stream()
            .map(FieldError::getField)
            .toList();
    }
}
