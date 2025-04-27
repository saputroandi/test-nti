package com.test.nti.dto;

import java.util.List;

import lombok.Data;

@Data
public class ClassRequestDto {
    
    private Long teacherId;

    // private List<Long> studentsId;

    private String grade;

    private String name;
}
