package com.jamapi.emarenda.domain.grade.repository;

import com.jamapi.emarenda.domain.grade.entity.GradeEntity;
import com.jamapi.emarenda.domain.school.entity.SchoolEntity;

import java.util.Optional;

public interface GradeRepository {
    Optional<GradeEntity> findById(long gradeId);
}
