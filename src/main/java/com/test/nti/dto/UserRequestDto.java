package com.test.nti.dto;

import com.test.nti.validator.constraint.PasswordMatch;
import com.test.nti.validator.constraint.RoleType;
import com.test.nti.validator.constraint.Unique;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@PasswordMatch(passwordField = "password", passwordConfirmationField = "passwordConfirmation")
public class UserRequestDto {

    @Email
    @Unique(columnName = "email", tableName = "users")
    private String email;

    @NotNull
    @Size(min = 8, message = "password must be at least 8 characters")
    private String password;

    @NotNull
    @Size(min = 8, message = "password must be at least 8 characters")
    private String passwordConfirmation;
}
