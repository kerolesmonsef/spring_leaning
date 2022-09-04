package com.example.qgame.validations.impls;

import com.example.qgame.validations.FileValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class FileValidator implements ConstraintValidator<FileValidation, MultipartFile> {

    private boolean required = false;
    private boolean requiredOnCreateOnly = false;
    private String[] types;

    @Autowired
    private HttpServletRequest request;

    @Override
    public void initialize(FileValidation validation) {
        required = validation.required();
        types = validation.types();
        requiredOnCreateOnly = validation.requiredOnCreateOnly();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {

        boolean emptyFile = (file == null || file.isEmpty());
        boolean isMethodPost = request.getMethod().equals("POST");


        if (requiredOnCreateOnly && emptyFile && isMethodPost) {
            changeMessage(context, "file must not be empty");
            return false;
        }

        if (required && emptyFile) {
            changeMessage(context, "file must not be empty");
            return false;
        }


        return true;
    }

    private void changeMessage(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

    }
}
