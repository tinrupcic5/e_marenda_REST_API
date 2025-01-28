package com.jamapi.emarenda.domain.lunch_attendance.repository;

import com.jamapi.emarenda.domain.lunch_attendance.entity.LunchAttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LunchAttendanceJpaRepository extends JpaRepository<LunchAttendanceEntity, Long> {
}
