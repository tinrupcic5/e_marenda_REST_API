package com.teapot.emarenda.domain.grade.repository;

import com.teapot.emarenda.domain.grade.entity.GradeEntity;

import java.util.Optional;

public interface GradeRepository {
    Optional<GradeEntity> findById(Long gradeId);
}
