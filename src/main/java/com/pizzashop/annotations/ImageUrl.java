package com.pizzashop.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Bartosz Pigla on 1/23/17.
 */
@Documented
@Constraint(validatedBy = ImageUrlValidator.class)
@Target( FIELD)
@Retention(RUNTIME)
public @interface ImageUrl {
    String message() default "{imageUrl.error}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
