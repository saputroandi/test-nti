package com.test.nti.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.nti.dto.ApiResponseDto;
import com.test.nti.dto.AuthRequestDto;
import com.test.nti.dto.AuthResponseDto;
import com.test.nti.dto.UserRequestDto;
import com.test.nti.entities.UserEntity;
import com.test.nti.enums.Role;
import com.test.nti.repositories.UserRepository;

@Service
public class AuthService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PasswordEncoder encoder;

        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private JWTService jwtService;

        @Transactional
        public ApiResponseDto<?, ?> register(UserRequestDto requestDto) {

                String encodedPassword = encoder.encode(requestDto.getPassword());

                UserEntity user = UserEntity.builder().email(requestDto.getEmail()).password(encodedPassword)
                                .role(Role.STUDENT)
                                .build();

                UserEntity savedUserEntity = userRepository.save(user);

                return ApiResponseDto.builder().data(savedUserEntity).build();
        }

        public AuthResponseDto login(AuthRequestDto requestDto) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                requestDto.getEmail(),
                                                requestDto.getPassword()));

                UserEntity user = userRepository.findByEmail(requestDto.getEmail())
                                .orElseThrow(() -> new IllegalArgumentException("email or password is wrong"));

                String token = jwtService.generateToken(user);

                return AuthResponseDto.builder().token(token).build();
        }
}
