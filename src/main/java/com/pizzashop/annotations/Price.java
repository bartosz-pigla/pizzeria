package com.pizzashop.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by barte on 31/12/2016.
 */
@Documented
@Constraint(validatedBy = PriceRangeValidator.class)
@Target( FIELD)
@Retention(RUNTIME)
public @interface Price {
    String message() default "{price.error}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
