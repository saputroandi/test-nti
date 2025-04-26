package com.test.nti.controllers;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.nti.dto.ApiResponseDto;
import com.test.nti.dto.PagingResponseDto;
import com.test.nti.dto.UserResponseDto;
import com.test.nti.dto.UserUpdateRequestDto;
import com.test.nti.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<UserResponseDto>, Void>> getAllUser(
            @PageableDefault(page = 0, size = 25, sort = "id", direction = Direction.DESC) Pageable pageable) {
        List<UserResponseDto> users = userService.getAllUser(pageable);
        ApiResponseDto<List<UserResponseDto>, Void> response = ApiResponseDto.<List<UserResponseDto>, Void>builder()
                .data(users)
                .errors(null)
                .paging(PagingResponseDto.builder().currentPage(pageable.getPageNumber()).size(pageable.getPageSize()).build())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponseDto<UserResponseDto, Void>> getUser(@PathVariable Long userId) {
        UserResponseDto user = userService.getUser(userId);
        ApiResponseDto<UserResponseDto, Void> response = ApiResponseDto.<UserResponseDto, Void>builder()
                .data(user)
                .errors(null)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<ApiResponseDto<UserResponseDto, Void>> updateUser(@PathVariable Long userId,
            UserUpdateRequestDto requestDto) {
        UserResponseDto user = userService.updateUser(userId, requestDto);
        ApiResponseDto<UserResponseDto, Void> response = ApiResponseDto.<UserResponseDto, Void>builder()
                .data(user)
                .errors(null)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        
        return ResponseEntity.noContent().build();
    }

}
