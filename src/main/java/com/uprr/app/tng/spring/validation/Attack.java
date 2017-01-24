package com.uprr.app.tng.spring.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

/**
 * Created by david on 1/23/17.
 */
@NotNull(message = "{validation.attack.null}")
@Min(value = 1, message = "{validation.attack.too_small}")
@Target({
    FIELD,
    PARAMETER,
    METHOD,
    ANNOTATION_TYPE,
    LOCAL_VARIABLE
})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {})
@Documented
public @interface Attack {
    String message() default  "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
