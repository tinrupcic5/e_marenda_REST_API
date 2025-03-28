package com.teapot.emarenda.domain.school_holiday.service;

import com.teapot.emarenda.domain.school_holiday.model.SchoolHolidayModel;

import java.time.LocalDate;
import java.util.Optional;

public interface SchoolHolidayService {
    Optional<SchoolHolidayModel> findByNonWorkingDate(LocalDate nonWorkingDate);
}
