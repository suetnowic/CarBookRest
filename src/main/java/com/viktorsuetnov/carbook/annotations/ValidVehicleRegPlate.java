package com.viktorsuetnov.carbook.annotations;

import com.viktorsuetnov.carbook.validations.VehicleRegistrationPlateValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VehicleRegistrationPlateValidator.class)
@Documented
public @interface ValidVehicleRegPlate {

    String message() default "Invalid vehicle registration plate";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
