package com.teapot.emarenda.domain.school_holiday.repository;

import com.teapot.emarenda.domain.school_holiday.entity.SchoolHolidayEntity;

import java.time.LocalDate;
import java.util.Optional;

public interface SchoolHolidayRepository {
    Optional<SchoolHolidayEntity> findByNonWorkingDate(LocalDate nonWorkingDate);
}
