package com.teapot.emarenda.domain.lunch_attendance.repository;

import com.teapot.emarenda.domain.lunch_attendance.entity.LunchAttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LunchAttendanceJpaRepository extends JpaRepository<LunchAttendanceEntity, Long> {
    List<LunchAttendanceEntity> findByUserEntityId(Long studentId);
    List<LunchAttendanceEntity> findByLunchDate(LocalDate date);
    Optional<LunchAttendanceEntity> findByUserEntityIdAndLunchDate(Long studentId, LocalDate date);
}
