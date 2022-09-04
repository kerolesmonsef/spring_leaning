package com.example.qgame.validations;

import com.example.qgame.validations.impls.FileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FileValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileValidation {
    String message() default "file validation error";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean required() default false;

    boolean requiredOnCreateOnly() default true;

    String[] types() default {};
}