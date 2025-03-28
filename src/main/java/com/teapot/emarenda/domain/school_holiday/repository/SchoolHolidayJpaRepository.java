package com.teapot.emarenda.domain.school_holiday.repository;

import com.teapot.emarenda.domain.school_holiday.entity.SchoolHolidayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SchoolHolidayJpaRepository extends JpaRepository<SchoolHolidayEntity, Long> {
    Optional<SchoolHolidayEntity> findByNonWorkingDate(LocalDate nonWorkingDate);
}
