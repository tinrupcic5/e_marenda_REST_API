package com.teapot.emarenda.domain.school_holiday.service;

import com.teapot.emarenda.domain.school_holiday.model.SchoolHolidayModel;
import com.teapot.emarenda.domain.school_holiday.repository.SchoolHolidayRepository;
import com.teapot.emarenda.mapper.SchoolHolidayMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SchoolHolidayServiceImpl implements SchoolHolidayService {
    private final SchoolHolidayRepository schoolHolidayRepository;
    private final SchoolHolidayMapper schoolHolidayMapper;

    public SchoolHolidayServiceImpl(SchoolHolidayRepository schoolHolidayRepository, SchoolHolidayMapper schoolHolidayMapper) {
        this.schoolHolidayRepository = schoolHolidayRepository;
        this.schoolHolidayMapper = schoolHolidayMapper;
    }

    @Override
    public Optional<SchoolHolidayModel> findByNonWorkingDate(LocalDate nonWorkingDate) {
        return schoolHolidayRepository.findByNonWorkingDate(nonWorkingDate)
                .map(schoolHolidayMapper::toModel);
    }

    @Override
    public List<SchoolHolidayModel> findAll() {
        return schoolHolidayRepository.findAll().stream()
                .map(schoolHolidayMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SchoolHolidayModel save(SchoolHolidayModel holiday) {
        return schoolHolidayMapper.toModel(
                schoolHolidayRepository.save(schoolHolidayMapper.toEntity(holiday))
        );
    }

    @Override
    @Transactional
    public void deleteByDate(LocalDate date) {
        schoolHolidayRepository.deleteByNonWorkingDate(date);
    }
}
