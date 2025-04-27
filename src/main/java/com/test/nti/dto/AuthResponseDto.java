package com.test.nti.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDto {
    
    private String token;
}
