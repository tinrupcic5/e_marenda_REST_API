package com.jamapi.emarenda.domain.school.service;

import com.jamapi.emarenda.domain.school.entity.SchoolEntity;
import com.jamapi.emarenda.domain.school.repository.SchoolRepository;
import org.springframework.stereotype.Service;

@Service
public class SchoolServiceImpl implements SchoolService {
 private final SchoolRepository schoolRepository;

    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public SchoolEntity findById(long schoolId) {
        return schoolRepository.findById(schoolId).orElseThrow();
    }
}
