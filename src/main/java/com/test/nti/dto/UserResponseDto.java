package com.test.nti.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    
    private Long id;

    private String email;
}
