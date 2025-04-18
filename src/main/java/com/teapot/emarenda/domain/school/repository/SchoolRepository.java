package com.teapot.emarenda.domain.school.repository;

import com.teapot.emarenda.domain.school.entity.SchoolEntity;

import java.util.Optional;

public interface SchoolRepository {
    Optional<SchoolEntity> findById(Long schoolId);
}
