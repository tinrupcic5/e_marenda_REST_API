package com.jamapi.emarenda.domain.grade.service;

import com.jamapi.emarenda.domain.grade.entity.GradeEntity;

import java.util.Optional;

public interface GradeService {
    Optional<GradeEntity> findById(Long gradeId);

}
