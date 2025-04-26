package com.test.nti.validator;

import java.lang.reflect.Field;
import java.util.Optional;

import com.test.nti.validator.constraint.PasswordMatch;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, Object> {

    String passwordFieldName;
    String passwordConfirmationFieldName;

    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        passwordFieldName = constraintAnnotation.passwordField();
        passwordConfirmationFieldName = constraintAnnotation.passwordConfirmationField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        try {
            Field passwordField = value.getClass().getDeclaredField(passwordFieldName);
            Field confirmPasswordField = value.getClass().getDeclaredField(passwordConfirmationFieldName);

            passwordField.setAccessible(true);
            confirmPasswordField.setAccessible(true);

            String password = (String) passwordField.get(value);
            String confirmPassword = (String) confirmPasswordField.get(value);

            if (password == null || confirmPassword == null) {
                return false;
            }

            return password.equals(confirmPassword);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return false;
        }
    }

}
