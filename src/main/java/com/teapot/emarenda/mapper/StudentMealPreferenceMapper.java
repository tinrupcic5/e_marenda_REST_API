package com.teapot.emarenda.mapper;

import com.teapot.emarenda.domain.student_meal_preference.entity.StudentMealPreferenceEntity;
import com.teapot.emarenda.domain.student_meal_preference.model.StudentMealPreferenceModel;
import org.springframework.stereotype.Component;

@Component
public class StudentMealPreferenceMapper implements AbstractMapper<StudentMealPreferenceModel, StudentMealPreferenceEntity> {

    @Override
    public StudentMealPreferenceModel toModel(StudentMealPreferenceEntity entity) {
        if (entity == null) return null;
        return new StudentMealPreferenceModel(
                entity.getId(),
                entity.getStudentId(),
                entity.getDate(),
                entity.isHasPreference(),
                entity.getPreferenceType(),
                entity.getNotes()
        );
    }

    @Override
    public StudentMealPreferenceEntity toEntity(StudentMealPreferenceModel model) {
        if (model == null) return null;
        return new StudentMealPreferenceEntity(
                model.getId(),
                model.getStudentId(),
                model.getDate(),
                model.isHasPreference(),
                model.getPreferenceType(),
                model.getNotes()
        );
    }
} 