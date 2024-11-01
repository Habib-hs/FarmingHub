package com.project.farmingHub.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class ProductTypeValidator implements ConstraintValidator<ValidatorProductType , String> {
    @Override
    public boolean isValid(String productType, ConstraintValidatorContext constraintValidatorContext) {
        List<String> productTypes = Arrays.asList("VACCINATION" , "MEDICATION");
        return productTypes.contains(productType);
    }
}
