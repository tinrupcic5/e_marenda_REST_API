package com.teapot.emarenda.domain.student_meal_preference.repository;

import com.teapot.emarenda.domain.student_meal_preference.entity.StudentMealPreferenceEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentMealPreferenceRepositoryImpl implements StudentMealPreferenceRepository {
    private final StudentMealPreferenceJpaRepository studentMealPreferenceJpaRepository;

    public StudentMealPreferenceRepositoryImpl(StudentMealPreferenceJpaRepository studentMealPreferenceJpaRepository) {
        this.studentMealPreferenceJpaRepository = studentMealPreferenceJpaRepository;
    }

    @Override
    public List<StudentMealPreferenceEntity> findAll() {
        return studentMealPreferenceJpaRepository.findAll();
    }

    @Override
    public List<StudentMealPreferenceEntity> findByStudentId(Long studentId) {
        return studentMealPreferenceJpaRepository.findByStudentId(studentId);
    }

    @Override
    public Optional<StudentMealPreferenceEntity> findByStudentIdAndDate(Long studentId, LocalDate date) {
        return studentMealPreferenceJpaRepository.findByStudentIdAndDate(studentId, date);
    }

    @Override
    @Transactional
    public StudentMealPreferenceEntity save(StudentMealPreferenceEntity preference) {
        return studentMealPreferenceJpaRepository.save(preference);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        studentMealPreferenceJpaRepository.deleteById(id);
    }
} 