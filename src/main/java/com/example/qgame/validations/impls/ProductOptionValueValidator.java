package com.example.qgame.validations.impls;

import com.example.qgame.Models.OptionValue;
import com.example.qgame.Models.ProductOptionValue;
import com.example.qgame.helpers.dto.OptionValueDTO;
import com.example.qgame.validations.ProductOptionValueValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ProductOptionValueValidator implements ConstraintValidator<ProductOptionValueValidation, List<OptionValueDTO>> {
    @Override
    public void initialize(ProductOptionValueValidation constraintAnnotation) {

    }

    @Override
    public boolean isValid(List<OptionValueDTO> optionValues, ConstraintValidatorContext context) {

        if (optionValues == null) {
            return true;
        }

        for (int i = 0; i < optionValues.size(); i++) {
            OptionValueDTO optionValue = optionValues.get(i);
            if (optionValue.getValue() == null || "".equals(optionValue.getValue())) {
                changeMessage(context, "value number " + i + " must not be empty");
                return false;
            } else if (optionValue.getOptionName() == null|| "".equals(optionValue.getOptionName())) {
                changeMessage(context, "option number " + i + " must not be empty");
                return false;
            }
        }

        return true;
    }

    private void changeMessage(ConstraintValidatorContext context, String message) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
    }
}
