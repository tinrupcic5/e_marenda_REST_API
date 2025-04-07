package com.teapot.emarenda.domain.lunch_attendance.repository;

import com.teapot.emarenda.domain.lunch_attendance.entity.LunchAttendanceEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LunchAttendanceRepository {

    List<LunchAttendanceEntity> findAll();

    List<LunchAttendanceEntity> findByStudentId(Long studentId);

    List<LunchAttendanceEntity> findByDate(LocalDate date);

    Optional<LunchAttendanceEntity> findByStudentIdAndDate(Long studentId, LocalDate date);

    LunchAttendanceEntity save(LunchAttendanceEntity attendance);

    void deleteById(Long id);
}
