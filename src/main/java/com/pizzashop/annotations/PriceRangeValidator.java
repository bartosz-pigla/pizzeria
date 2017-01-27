package com.pizzashop.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

/**
 * Created by Bartosz Pigla on 31/12/2016.
 */
public class PriceRangeValidator implements ConstraintValidator<Price, Double> {
    Price price;
    @Override
    public void initialize(Price constraintAnnotation) {
        this.price=constraintAnnotation;
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return
                value.compareTo(new Double("0.0")) != -1 && value.compareTo(new Double("50.0")) != 1;
    }
}
