package com.nylgsc.myann;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AgeValidator implements ConstraintValidator<MoreThanZero,Integer> {
    @Override
    public void initialize(MoreThanZero constraintAnnotation) {

    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return value>0;
    }
}
