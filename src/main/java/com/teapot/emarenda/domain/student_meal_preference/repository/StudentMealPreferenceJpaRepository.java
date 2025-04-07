package com.teapot.emarenda.domain.student_meal_preference.repository;

import com.teapot.emarenda.domain.student_meal_preference.entity.StudentMealPreferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentMealPreferenceJpaRepository extends JpaRepository<StudentMealPreferenceEntity, Long> {
    List<StudentMealPreferenceEntity> findByStudentId(Long studentId);
    Optional<StudentMealPreferenceEntity> findByStudentIdAndDate(Long studentId, LocalDate date);
} 