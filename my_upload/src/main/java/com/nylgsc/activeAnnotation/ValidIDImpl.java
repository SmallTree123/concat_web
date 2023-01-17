package com.nylgsc.activeAnnotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidIDImpl implements ConstraintValidator<ValidID,Object> {

    private static final String IDPattern = "^[0-9a-zA-Z]$";

    @Override
    public void initialize(ValidID constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value instanceof String){
            return ((String)value).matches(IDPattern);
        }
        return false;
    }
}
