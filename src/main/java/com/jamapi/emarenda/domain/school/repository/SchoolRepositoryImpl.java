package com.jamapi.emarenda.domain.school.repository;

import com.jamapi.emarenda.domain.school.entity.SchoolEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SchoolRepositoryImpl implements SchoolRepository {
    private final SchoolJpaRepository schoolJpaRepository;

    public SchoolRepositoryImpl(SchoolJpaRepository schoolJpaRepository) {
        this.schoolJpaRepository = schoolJpaRepository;
    }

    @Override
    public Optional<SchoolEntity> findById(Long schoolId) {
        return schoolJpaRepository.findById(schoolId);
    }
}
