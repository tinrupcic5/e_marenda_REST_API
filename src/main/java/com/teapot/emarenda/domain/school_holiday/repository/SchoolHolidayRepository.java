package com.teapot.emarenda.domain.school_holiday.repository;

import com.teapot.emarenda.domain.school_holiday.entity.SchoolHolidayEntity;
import com.teapot.emarenda.domain.school_holiday.model.SchoolHolidayModel;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SchoolHolidayRepository {
    Optional<SchoolHolidayEntity> findByNonWorkingDate(LocalDate nonWorkingDate);
    List<SchoolHolidayEntity> findAll();
    SchoolHolidayEntity save(SchoolHolidayEntity holiday);
    void deleteByNonWorkingDate(LocalDate date);
}
