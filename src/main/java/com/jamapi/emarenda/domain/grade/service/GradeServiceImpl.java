package com.jamapi.emarenda.domain.grade.service;

import com.jamapi.emarenda.domain.grade.entity.GradeEntity;
import com.jamapi.emarenda.domain.grade.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {
 private final GradeRepository gradeRepository;

    public GradeServiceImpl(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }

    @Override
    public Optional<GradeEntity> findById(Long gradeId) {
        return gradeRepository.findById(gradeId);
    }
}
