package com.newland.lanhe.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author leell
 */
public class OptionsValidator implements ConstraintValidator<IntOptions, Object> {
    private int[] options = new int[0];

    @Override
    public void initialize(IntOptions constraintAnnotation) {
        options = constraintAnnotation.options();
        if (options.length == 0) {
            options = constraintAnnotation.value();
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return true;
        }
        boolean result = false;
        for (Object option : options) {
            if (value == option) {
                result = true;
                break;
            }
        }

        return result;
    }
}
