package com.test.nti.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthRequestDto {
    @NotBlank
    @Size(max = 12)
    private String email;

    @NotBlank
    @Size(max = 100)
    private String password;
}
