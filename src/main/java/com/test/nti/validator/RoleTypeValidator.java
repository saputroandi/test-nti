package com.test.nti.validator;

import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.test.nti.enums.Role;
import com.test.nti.validator.constraint.RoleType;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleTypeValidator implements ConstraintValidator<RoleType, String> {

    private static final Set<String> ROLE_TYPES = EnumSet.allOf(Role.class).stream().map(Enum::name).collect(Collectors.toSet());

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        
        if(ROLE_TYPES.contains(value) || value == null) return true;

        return false;
    }
}
