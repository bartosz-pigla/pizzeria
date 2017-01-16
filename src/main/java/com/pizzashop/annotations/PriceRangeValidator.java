package com.pizzashop.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

/**
 * Created by barte on 31/12/2016.
 */
public class PriceRangeValidator implements ConstraintValidator<Price, BigDecimal> {
    Price price;
    @Override
    public void initialize(Price constraintAnnotation) {
        this.price=constraintAnnotation;
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        return
                value.compareTo(new BigDecimal("0.0")) != -1 && value.compareTo(new BigDecimal("50.0")) != 1;
    }
}
