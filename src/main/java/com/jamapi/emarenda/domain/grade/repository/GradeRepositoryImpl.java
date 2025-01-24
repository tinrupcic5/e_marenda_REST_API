package com.jamapi.emarenda.domain.grade.repository;

import com.jamapi.emarenda.domain.grade.entity.GradeEntity;
import com.jamapi.emarenda.domain.school.entity.SchoolEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class GradeRepositoryImpl implements GradeRepository {
    private final GradeJpaRepository gradeJpaRepository;

    public GradeRepositoryImpl(GradeJpaRepository gradeJpaRepository) {
        this.gradeJpaRepository = gradeJpaRepository;
    }

    @Override
    public Optional<GradeEntity> findById(long gradeId) {
        return gradeJpaRepository.findById(gradeId);
    }
}
