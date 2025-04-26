package com.test.nti.validator;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Component;

import com.test.nti.validator.constraint.Unique;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueValidator implements ConstraintValidator<Unique, String> {

    private final JdbcClient client;
    private String columnName;
    private String tableName;

    public UniqueValidator(JdbcClient client){
        this.client = client;
    }

    @Override
    public void initialize(Unique constraintAnnotation) {
        columnName = constraintAnnotation.columnName();
        tableName = constraintAnnotation.tableName();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return client.sql("select count(*) from " + tableName + " where " + columnName + " = ? ")
        .param(value)
        .query(Integer.class)
        .single() == 0;
    }
    
}
