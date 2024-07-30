package app.sport.sw.annotation;

import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DateRange {

    int month();
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
