package com.example.qgame.validations;

import com.example.qgame.validations.impls.ExistsValidator;
import com.example.qgame.validations.impls.FileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExistsValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Exists {
    String message() default "validation error message";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String entity();

    String column();
}
