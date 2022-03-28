package com.viktorsuetnov.carbook.annotations;

import com.viktorsuetnov.carbook.validations.PasswordMatchesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface PasswordMatches {

    String message() default "password do not match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
