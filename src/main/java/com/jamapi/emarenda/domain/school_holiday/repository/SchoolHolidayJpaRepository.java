package com.jamapi.emarenda.domain.school_holiday.repository;

import com.jamapi.emarenda.domain.school_holiday.entity.SchoolHolidayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolHolidayJpaRepository extends JpaRepository<SchoolHolidayEntity, Long> {
}
