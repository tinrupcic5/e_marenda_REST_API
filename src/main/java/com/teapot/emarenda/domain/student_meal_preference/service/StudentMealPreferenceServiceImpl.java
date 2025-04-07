package com.teapot.emarenda.domain.student_meal_preference.service;

import com.teapot.emarenda.domain.student_meal_preference.model.StudentMealPreferenceModel;
import com.teapot.emarenda.domain.student_meal_preference.repository.StudentMealPreferenceRepository;
import com.teapot.emarenda.mapper.StudentMealPreferenceMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentMealPreferenceServiceImpl implements StudentMealPreferenceService {
    private final StudentMealPreferenceRepository studentMealPreferenceRepository;
    private final StudentMealPreferenceMapper studentMealPreferenceMapper;

    public StudentMealPreferenceServiceImpl(StudentMealPreferenceRepository studentMealPreferenceRepository, StudentMealPreferenceMapper studentMealPreferenceMapper) {
        this.studentMealPreferenceRepository = studentMealPreferenceRepository;
        this.studentMealPreferenceMapper = studentMealPreferenceMapper;
    }

    @Override
    public List<StudentMealPreferenceModel> findAll() {
        return studentMealPreferenceRepository.findAll().stream()
                .map(studentMealPreferenceMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentMealPreferenceModel> findByStudentId(Long studentId) {
        return studentMealPreferenceRepository.findByStudentId(studentId).stream()
                .map(studentMealPreferenceMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StudentMealPreferenceModel> findByStudentIdAndDate(Long studentId, LocalDate date) {
        return studentMealPreferenceRepository.findByStudentIdAndDate(studentId, date)
                .map(studentMealPreferenceMapper::toModel);
    }

    @Override
    @Transactional
    public StudentMealPreferenceModel save(StudentMealPreferenceModel preference) {
        return studentMealPreferenceMapper.toModel(
                studentMealPreferenceRepository.save(studentMealPreferenceMapper.toEntity(preference))
        );
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        studentMealPreferenceRepository.deleteById(id);
    }
} 