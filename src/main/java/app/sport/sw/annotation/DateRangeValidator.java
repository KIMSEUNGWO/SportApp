package app.sport.sw.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

public class DateRangeValidator implements ConstraintValidator<DateRange, LocalDateTime> {

    private int months;

    @Override
    public void initialize(DateRange constraintAnnotation) {
        months = constraintAnnotation.month();
    }

    @Override
    public boolean isValid(LocalDateTime date, ConstraintValidatorContext constraintValidatorContext) {
        if (date == null) return true;

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime target = now.minusMonths(months);

        return !date.isBefore(target) && !date.isAfter(now);
    }
}
