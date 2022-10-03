package com.example.qgame.validations;

import com.example.qgame.validations.impls.UniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UniqueValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {
    String message() default "validation error message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String entity();

    String column();
}
