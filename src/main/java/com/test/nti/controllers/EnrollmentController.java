package com.test.nti.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.nti.dto.ApiResponseDto;
import com.test.nti.dto.EnrollmentRequestDto;
import com.test.nti.dto.EnrollmentResponseDto;
import com.test.nti.dto.PagingResponseDto;
import com.test.nti.dto.UserResponseDto;
import com.test.nti.services.EnrollementService;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollementService enrollementService;

    @PostMapping("/{classId}")
    @PreAuthorize("hasAnyRole({'TEACHER', 'ADMIN'})")
    public ResponseEntity<ApiResponseDto<EnrollmentResponseDto, Void>> enrollStrudentIntoClass(
            @PathVariable Long classId, @RequestBody EnrollmentRequestDto requestDto) {
        EnrollmentResponseDto enrollmentResponseDto = enrollementService
                .enrollStudentIntoClass(requestDto.getStudentId(), classId);

        ApiResponseDto<EnrollmentResponseDto, Void> res = ApiResponseDto.<EnrollmentResponseDto, Void>builder()
                .data(enrollmentResponseDto).build();

        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<EnrollmentResponseDto>, Void>> getAllEnrollment(
            @PageableDefault(page = 0, size = 25, sort = "id", direction = Direction.DESC) Pageable pageable) {
        List<EnrollmentResponseDto> enrollmentResponseDtos = enrollementService.getAllEnrollments(pageable);

        ApiResponseDto<List<EnrollmentResponseDto>, Void> response = ApiResponseDto
                .<List<EnrollmentResponseDto>, Void>builder()
                .data(enrollmentResponseDtos)
                .errors(null)
                .paging(PagingResponseDto.builder().currentPage(pageable.getPageNumber()).size(pageable.getPageSize())
                        .build())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
