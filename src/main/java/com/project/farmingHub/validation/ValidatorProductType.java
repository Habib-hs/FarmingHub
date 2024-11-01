package com.project.farmingHub.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

import static com.project.farmingHub.values.Messages.INVALID_PRODUCT_TYPE;

@Target({ElementType.FIELD , ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ProductTypeValidator.class)
public @interface ValidatorProductType {

    public String message () default INVALID_PRODUCT_TYPE;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
