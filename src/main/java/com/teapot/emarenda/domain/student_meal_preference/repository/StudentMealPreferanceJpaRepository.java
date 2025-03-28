package com.teapot.emarenda.domain.student_meal_preference.repository;

import com.teapot.emarenda.domain.student_meal_preference.entity.StudentMealPreferenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentMealPreferanceJpaRepository extends JpaRepository<StudentMealPreferenceEntity, Long> {
}
