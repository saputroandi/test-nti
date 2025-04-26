package com.test.nti.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.test.nti.entities.UserEntity;
import com.test.nti.enums.Role;
import com.test.nti.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;

    public DatabaseSeeder(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();

        UserEntity admin = new UserEntity("admin@example.com", "passwordadmin", Role.ADMIN);
        
        UserEntity teacher = new UserEntity("teacher@example.com", "passwordteacher", Role.TEACHER);

        UserEntity student1 = new UserEntity("student1@example.com", "passwordstudent1", Role.STUDENT);
        UserEntity student2 = new UserEntity("student2@example.com", "passwordstudent2", Role.STUDENT);
        UserEntity student3 = new UserEntity("student3@example.com", "passwordstudent3", Role.STUDENT);

        userRepository.save(admin);
        userRepository.save(teacher);
        userRepository.save(student1);
        userRepository.save(student2);
        userRepository.save(student3);

        log.info("Database has been seeded with users.");
    }
}
