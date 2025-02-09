package com.jamapi.emarenda.domain.school.repository;

import com.jamapi.emarenda.domain.school.entity.SchoolEntity;

import java.util.Optional;

public interface SchoolRepository {
    Optional<SchoolEntity> findById(Long schoolId);
}
