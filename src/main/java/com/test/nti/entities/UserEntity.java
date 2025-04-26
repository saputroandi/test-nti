package com.test.nti.entities;

import com.test.nti.enums.Role;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "users")
public class UserEntity extends BaseEntity {
    
    private String email;

    private String password;

    // @ManyToOne
    // @JoinColumn(name = "school_class_id")
    // private SchoolClass schoolClass;

    @Enumerated(EnumType.STRING)
    private Role role;
}
