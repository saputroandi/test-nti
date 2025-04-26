package com.test.nti.dto;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserUpdateRequestDto {

    @Email
    private String email;
    
    private String password;
}
