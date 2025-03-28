package com.teapot.emarenda.domain.student_meal_preference.entity;

import com.teapot.emarenda.rbac.entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Entity
@Table(name = "student_meal_preference")
@Schema(description = "Entity representing a student's meal preferences or dietary requirements")
public class StudentMealPreferenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the meal preference record")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Schema(description = "Student for whom this preference is recorded")
    private UserEntity userEntity;

    @Column(nullable = false)
    @Schema(description = "Comment or description of the meal preference or dietary requirement")
    private String comment;

}