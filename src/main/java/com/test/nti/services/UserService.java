package com.test.nti.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.test.nti.dto.UserResponseDto;
import com.test.nti.dto.UserUpdateRequestDto;
import com.test.nti.entities.UserEntity;
import com.test.nti.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponseDto> getAllUser(Pageable page) {
        return userRepository.findAll(page).stream()
                .map(user -> UserResponseDto.builder().id(user.getId()).email(user.getEmail()).name(user.getName())
                        .role(user.getRole().toString())
                        .build())
                .toList();
    }

    public UserResponseDto getUser(Long id) {
        UserEntity res = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("user tidak di temukan"));

        return UserResponseDto.builder().id(res.getId()).email(res.getEmail()).role(res.getRole().toString())
                .name(res.getName()).build();
    }

    public UserResponseDto updateUser(Long id, UserUpdateRequestDto updateRequestDto) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("user tidak di temukan"));

        if (!updateRequestDto.getName().isEmpty()) {
            user.setEmail(updateRequestDto.getName());
        }

        if (!updateRequestDto.getPassword().isEmpty()) {
            user.setPassword(updateRequestDto.getPassword());
        }

        UserEntity updatedUser = userRepository.save(user);

        return UserResponseDto.builder().id(updatedUser.getId()).role(updatedUser.getRole().toString())
                .email(updatedUser.getEmail())
                .name(updatedUser.getName()).build();
    }

    public void deleteUser(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("user tidak di temukan"));

        userRepository.delete(user);
    }
}
