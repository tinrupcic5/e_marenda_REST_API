package com.teapot.emarenda.domain.grade.service;

import com.teapot.emarenda.domain.grade.entity.GradeEntity;

import java.util.Optional;

public interface GradeService {
    Optional<GradeEntity> findById(Long gradeId);

}
