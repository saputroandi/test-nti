package com.test.nti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.nti.dto.ApiResponseDto;
import com.test.nti.dto.AuthRequestDto;
import com.test.nti.dto.AuthResponseDto;
import com.test.nti.dto.UserRequestDto;
import com.test.nti.services.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDto<?, ?>> register(@RequestBody UserRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<AuthResponseDto, Object>> login(@RequestBody AuthRequestDto request) {

        AuthResponseDto token = authService.login(request);

        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponseDto.<AuthResponseDto, Object>builder()
                        .data(token).build());
    }
}
