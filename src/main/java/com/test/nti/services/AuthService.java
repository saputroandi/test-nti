package com.test.nti.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.test.nti.dto.ApiResponseDto;
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

    @Transactional
    public ApiResponseDto<?, ?> register(UserRequestDto requestDto){

        String encodedPassword = encoder.encode(requestDto.getPassword());

        UserEntity user = UserEntity.builder().email(requestDto.getEmail()).password(encodedPassword).role(Role.STUDENT).build();
        
        UserEntity savedUserEntity = userRepository.save(user);

        return ApiResponseDto.builder().data(savedUserEntity).build();
    }
}
