package com.example.qgame.validations.impls;

import com.example.qgame.validations.ContactNumberConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ContactNumberValidator implements ConstraintValidator<ContactNumberConstraint, String> {

    private int number;

    @Override
    public void initialize(ContactNumberConstraint contactNumber) {
        this.number = contactNumber.number();
    }

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
        return contactField != null && contactField.matches("[0-9]+") && (contactField.length() == number) && (contactField.length() < 14);
    }

}