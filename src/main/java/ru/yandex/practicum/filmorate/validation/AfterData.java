package ru.yandex.practicum.filmorate.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AfterDateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AfterData {
    String message() default "Дата должна быть позже указанной даты";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String value() default "0000-00-00";

    String pattern() default "yyyy-MM-dd";
}
