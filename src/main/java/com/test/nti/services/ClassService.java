package com.test.nti.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import com.test.nti.dto.ClassRequestDto;
import com.test.nti.dto.ClassResponseDto;
import com.test.nti.dto.UserResponseDto;
import com.test.nti.entities.ClassEntity;
import com.test.nti.entities.UserEntity;
import com.test.nti.enums.Role;
import com.test.nti.repositories.ClassRepository;
import com.test.nti.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClassService {

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private UserRepository userRepository;

    public ClassResponseDto createClass(ClassRequestDto requestDto) {
        UserEntity teacher = userRepository.findById(requestDto.getTeacherId())
                .orElseThrow(() -> new IllegalArgumentException("teacher tidak di temukan"));

        if (!Role.TEACHER.toString().equals(teacher.getRole().toString())) {
            throw new IllegalArgumentException("Only teachers can create a classroom");
        }

        ClassEntity newClass = ClassEntity.builder()
                .name(requestDto.getName())
                .grade(requestDto.getGrade())
                .teacher(teacher)
                .build();

        ClassEntity savedClass = classRepository.save(newClass);

        return ClassResponseDto.builder()
                .name(savedClass.getName())
                .grade(savedClass.getGrade())
                .teacherName(savedClass.getTeacher().getName())
                .build();
    }

    public List<ClassResponseDto> getAllClass(Pageable page) {
        return classRepository.findAll(page).stream()
                .map(classEntity -> ClassResponseDto.builder()
                        .name(classEntity.getName())
                        .grade(classEntity.getGrade())
                        .teacherName(classEntity.getTeacher().getName())
                        .build())
                .toList();
    }
}
