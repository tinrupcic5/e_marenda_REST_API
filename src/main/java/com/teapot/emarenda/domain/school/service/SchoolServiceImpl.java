package com.teapot.emarenda.domain.school.service;

import com.teapot.emarenda.domain.school.entity.SchoolEntity;
import com.teapot.emarenda.domain.school.repository.SchoolRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SchoolServiceImpl implements SchoolService {
 private final SchoolRepository schoolRepository;

    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public Optional<SchoolEntity> findById(Long schoolId) {
        return schoolRepository.findById(schoolId);
    }


}
