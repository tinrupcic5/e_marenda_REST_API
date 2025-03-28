package com.teapot.emarenda.domain.grade.repository;

import com.teapot.emarenda.domain.grade.entity.GradeEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class GradeRepositoryImpl implements GradeRepository {
    private final GradeJpaRepository gradeJpaRepository;

    public GradeRepositoryImpl(GradeJpaRepository gradeJpaRepository) {
        this.gradeJpaRepository = gradeJpaRepository;
    }

    @Override
    public Optional<GradeEntity> findById(Long gradeId) {
        return gradeJpaRepository.findById(gradeId);
    }
}
