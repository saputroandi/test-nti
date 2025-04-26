package com.test.nti.validator.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.test.nti.validator.UniqueValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = UniqueValidator.class)
@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Unique {
    
    String message() default "email already exist";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String columnName();
    String tableName();
}
