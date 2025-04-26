package com.test.nti.validator.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.test.nti.validator.RoleTypeValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = RoleTypeValidator.class)
@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RoleType {

    String message() default "role does not exist";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    
}
