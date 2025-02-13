package com.jamapi.emarenda.domain.school_holiday.service;

import com.jamapi.emarenda.domain.school_holiday.model.SchoolHolidayModel;
import com.jamapi.emarenda.domain.school_holiday.repository.SchoolHolidayRepository;
import com.jamapi.emarenda.mapper.SchoolHolidayMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

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

}
