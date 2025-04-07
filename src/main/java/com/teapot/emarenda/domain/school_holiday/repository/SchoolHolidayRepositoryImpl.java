package com.teapot.emarenda.domain.school_holiday.repository;

import com.teapot.emarenda.domain.school_holiday.entity.SchoolHolidayEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
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

    @Override
    public List<SchoolHolidayEntity> findAll() {
        return schoolHolidayJpaRepository.findAll();
    }

    @Override
    @Transactional
    public SchoolHolidayEntity save(SchoolHolidayEntity holiday) {
        return schoolHolidayJpaRepository.save(holiday);
    }

    @Override
    @Transactional
    public void deleteByNonWorkingDate(LocalDate date) {
        schoolHolidayJpaRepository.deleteByNonWorkingDate(date);
    }
}
