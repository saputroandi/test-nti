package com.test.nti.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.nti.dto.ApiResponseDto;
import com.test.nti.dto.ClassRequestDto;
import com.test.nti.dto.ClassResponseDto;
import com.test.nti.dto.PagingResponseDto;
import com.test.nti.dto.UserResponseDto;
import com.test.nti.services.ClassService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/classes")
public class ClassController {

    @Autowired
    private ClassService classService;

    @PostMapping
    public ResponseEntity<ClassResponseDto> createNewClass(@RequestBody ClassRequestDto requestDto) {

        ClassResponseDto res = classService.createClass(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<ClassResponseDto>, Void>> getAllClass(
            @PageableDefault(page = 0, size = 25, sort = "id", direction = Direction.DESC) Pageable pageable) {
        List<ClassResponseDto> classes = classService.getAllClass(pageable);

        ApiResponseDto<List<ClassResponseDto>, Void> response = ApiResponseDto.<List<ClassResponseDto>, Void>builder()
                .data(classes)
                .errors(null)
                .paging(PagingResponseDto.builder().currentPage(pageable.getPageNumber()).size(pageable.getPageSize())
                        .build())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
