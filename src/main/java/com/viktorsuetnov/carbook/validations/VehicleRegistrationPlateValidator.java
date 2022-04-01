package com.viktorsuetnov.carbook.validations;

import com.viktorsuetnov.carbook.annotations.ValidVehicleRegPlate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VehicleRegistrationPlateValidator implements ConstraintValidator<ValidVehicleRegPlate, String> {

   private static final String VRP_PLATE = "^[АВЕКМНОРСТУХ]\\d{3}(?<!000)[АВЕКМНОРСТУХ]{2}\\d{2,3}$";

   public void initialize(ValidVehicleRegPlate constraint) {
   }

   public boolean isValid(String value, ConstraintValidatorContext context) {
      return validateVRP(value);
   }

   private boolean validateVRP(String value) {
      Pattern pattern = Pattern.compile(VRP_PLATE);
      Matcher matcher = pattern.matcher(value);
      return matcher.matches();
   }
}
