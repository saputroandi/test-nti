package com.test.nti.dto;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClassResponseDto {
    
    private String name;
    
    private String grade;

    private String teacherName;

    // private List<UserResponseDto> students;

}
