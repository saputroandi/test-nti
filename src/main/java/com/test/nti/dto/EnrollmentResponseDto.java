package com.test.nti.dto;

import java.time.Instant;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnrollmentResponseDto {
    
    private String studentName;

    private Instant enrollmentDate;

    private String className;
}
