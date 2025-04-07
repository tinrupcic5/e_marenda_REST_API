package com.teapot.emarenda.domain.student_meal_preference.entity;

import com.teapot.emarenda.rbac.entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "student_meal_preferences")
@Schema(description = "Entity representing a student's meal preferences or dietary requirements")
public class StudentMealPreferenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the meal preference record")
    private Long id;

    @Column(name = "student_id", nullable = false)
    @Schema(description = "Student for whom this preference is recorded")
    private Long studentId;

    @Column(nullable = false)
    @Schema(description = "Date of the meal preference or dietary requirement")
    private LocalDate date;

    @Column(name = "has_preference", nullable = false)
    @Schema(description = "Indicates whether the student has a meal preference or dietary requirement")
    private boolean hasPreference;

    @Column(name = "preference_type")
    @Schema(description = "Type of the meal preference or dietary requirement")
    private String preferenceType;

    @Column(length = 500)
    @Schema(description = "Additional notes or comments about the meal preference or dietary requirement")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Schema(description = "Student for whom this preference is recorded")
    private UserEntity userEntity;

    @Column(nullable = false)
    @Schema(description = "Comment or description of the meal preference or dietary requirement")
    private String comment;

    public StudentMealPreferenceEntity() {
    }

    public StudentMealPreferenceEntity(Long id, Long studentId, LocalDate date, boolean hasPreference, String preferenceType, String notes) {
        this.id = id;
        this.studentId = studentId;
        this.date = date;
        this.hasPreference = hasPreference;
        this.preferenceType = preferenceType;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isHasPreference() {
        return hasPreference;
    }

    public void setHasPreference(boolean hasPreference) {
        this.hasPreference = hasPreference;
    }

    public String getPreferenceType() {
        return preferenceType;
    }

    public void setPreferenceType(String preferenceType) {
        this.preferenceType = preferenceType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}