package com.test.nti.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "classes")
public class ClassEntity extends BaseEntity {
    
    private String name;

    private String grade;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private UserEntity teacher;

    @OneToMany(mappedBy = "classEntity")
    private List<EnrollmentEntity> enrollmentEntities;
}
