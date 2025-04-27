package com.test.nti.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.test.nti.entities.UserEntity;
import com.test.nti.enums.Role;
import com.test.nti.repositories.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    public DatabaseSeeder(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();

        UserEntity admin = new UserEntity("admin@example.com", encoder.encode("passwordadmin"), "admin", Role.ADMIN);
        
        UserEntity teacher = new UserEntity("teacher@example.com", encoder.encode("passwordteacher"), "arum",
                Role.TEACHER);

        UserEntity student1 = new UserEntity("student1@example.com", encoder.encode("passwordstudent1"), "andi",
                Role.STUDENT);
        UserEntity student2 = new UserEntity("student2@example.com", encoder.encode("passwordstudent2"), "budi",
                Role.STUDENT);
        UserEntity student3 = new UserEntity("student3@example.com", encoder.encode("passwordstudent3"), "maulana",
                Role.STUDENT);

        userRepository.save(admin);
        userRepository.save(teacher);
        userRepository.save(student1);
        userRepository.save(student2);
        userRepository.save(student3);

        log.info("Database has been seeded with users.");
    }
}
