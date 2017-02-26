package com.pizzashop.annotations;

import com.pizzashop.models.Manager;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by bartek on 2/26/17.
 */
public class SamePasswordsValidator  implements ConstraintValidator<SamePasswords,Manager> {
    SamePasswords samePasswordsAnnotation;

    @Override
    public void initialize(SamePasswords samePasswordsAnnotation) {
        this.samePasswordsAnnotation=samePasswordsAnnotation;
    }

    @Override
    public boolean isValid(Manager value, ConstraintValidatorContext context) {
        return value.getPassword().equals(value.getPasswordConfirmation());
    }
}
