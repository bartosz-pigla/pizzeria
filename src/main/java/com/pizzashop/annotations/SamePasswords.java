package com.pizzashop.annotations;

/**
 * Created by bartek on 2/26/17.
 */

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = SamePasswordsValidator.class)
@Target(TYPE )
@Retention(RUNTIME)

public @interface SamePasswords {

    String message() default "{samePassword.error}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
