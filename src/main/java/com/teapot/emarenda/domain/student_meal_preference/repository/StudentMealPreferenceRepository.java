package com.teapot.emarenda.domain.student_meal_preference.repository;

import com.teapot.emarenda.domain.student_meal_preference.entity.StudentMealPreferenceEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudentMealPreferenceRepository {
    List<StudentMealPreferenceEntity> findAll();
    List<StudentMealPreferenceEntity> findByStudentId(Long studentId);
    Optional<StudentMealPreferenceEntity> findByStudentIdAndDate(Long studentId, LocalDate date);
    StudentMealPreferenceEntity save(StudentMealPreferenceEntity preference);
    void deleteById(Long id);
} 