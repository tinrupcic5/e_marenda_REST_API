package com.jamapi.emarenda.domain.school_holiday.repository;

import com.jamapi.emarenda.domain.school_holiday.entity.SchoolHolidayEntity;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public class SchoolHolidayRepositoryImpl implements SchoolHolidayRepository {
    private final SchoolHolidayJpaRepository schoolHolidayJpaRepository;

    public SchoolHolidayRepositoryImpl(SchoolHolidayJpaRepository schoolHolidayJpaRepository) {
        this.schoolHolidayJpaRepository = schoolHolidayJpaRepository;
    }

    @Override
    public Optional<SchoolHolidayEntity> findByNonWorkingDate(LocalDate nonWorkingDate) {
        return schoolHolidayJpaRepository.findByNonWorkingDate(nonWorkingDate);
    }
}
