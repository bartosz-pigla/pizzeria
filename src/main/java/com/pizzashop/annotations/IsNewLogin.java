package com.pizzashop.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by bartek on 2/26/17.
 */
@Documented
@Constraint(validatedBy = LoginValidator.class)
@Target( FIELD)
@Retention(RUNTIME)
public @interface IsNewLogin {
    String message() default "{isNewLogin.error}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
