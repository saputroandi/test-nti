package com.test.nti.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.nti.entities.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
    public Optional<UserEntity> findByEmail(String email);
}
