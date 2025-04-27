package com.test.nti.services;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.test.nti.dto.EnrollmentResponseDto;
import com.test.nti.entities.ClassEntity;
import com.test.nti.entities.EnrollmentEntity;
import com.test.nti.entities.UserEntity;
import com.test.nti.repositories.ClassRepository;
import com.test.nti.repositories.EnrollmentRepository;
import com.test.nti.repositories.UserRepository;

@Service
public class EnrollementService {

        @Autowired
        private EnrollmentRepository enrollmentRepository;

        @Autowired
        private ClassRepository classRepository;

        @Autowired
        private UserRepository userRepository;

        public EnrollmentResponseDto enrollStudentIntoClass(Long studentId, Long classId) {
                UserEntity student = userRepository.findById(studentId)
                                .orElseThrow(() -> new IllegalArgumentException("student not found"));

                ClassEntity classEntity = classRepository.findById(classId)
                                .orElseThrow(() -> new IllegalArgumentException("class not found"));

                boolean isAlreadyEnrolled = enrollmentRepository.existsByStudentAndClassEntity(student, classEntity);

                if (isAlreadyEnrolled) {
                        throw new IllegalArgumentException("Student is already enrolled in this class.");
                }

                EnrollmentEntity enrollmentEntity = EnrollmentEntity.builder()
                                .classEntity(classEntity)
                                .student(student)
                                .enrollmentDate(Instant.now())
                                .build();

                EnrollmentEntity savedEnrollment = enrollmentRepository.save(enrollmentEntity);

                return EnrollmentResponseDto.builder()
                                .className(savedEnrollment.getClassEntity().getName())
                                .enrollmentDate(savedEnrollment.getEnrollmentDate())
                                .studentName(savedEnrollment.getStudent().getName())
                                .build();
        }

        public List<EnrollmentResponseDto> getAllEnrollments(Pageable pageable) {
                List<EnrollmentResponseDto> res = enrollmentRepository.findAll().stream()
                                .map(data -> EnrollmentResponseDto.builder()
                                                .studentName(data.getStudent().getName())
                                                .className(data.getClassEntity().getName())
                                                .enrollmentDate(data.getEnrollmentDate())
                                                .build())
                                .toList();

                return res;
        }
}
