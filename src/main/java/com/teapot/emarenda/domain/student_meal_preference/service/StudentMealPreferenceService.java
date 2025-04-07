package com.teapot.emarenda.domain.student_meal_preference.service;

import com.teapot.emarenda.domain.student_meal_preference.model.StudentMealPreferenceModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StudentMealPreferenceService {
    List<StudentMealPreferenceModel> findAll();
    List<StudentMealPreferenceModel> findByStudentId(Long studentId);
    Optional<StudentMealPreferenceModel> findByStudentIdAndDate(Long studentId, LocalDate date);
    StudentMealPreferenceModel save(StudentMealPreferenceModel preference);
    void deleteById(Long id);
} 