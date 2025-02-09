package com.jamapi.emarenda.domain.school.service;

import com.jamapi.emarenda.domain.school.entity.SchoolEntity;

import java.util.Optional;

public interface SchoolService {
    Optional<SchoolEntity> findById(Long schoolId);

}
