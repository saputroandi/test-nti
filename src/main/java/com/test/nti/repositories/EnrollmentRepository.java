package com.test.nti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.nti.entities.ClassEntity;
import com.test.nti.entities.EnrollmentEntity;
import com.test.nti.entities.UserEntity;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity, Long> {
    boolean existsByStudentAndClassEntity(UserEntity student, ClassEntity classroom);
}
