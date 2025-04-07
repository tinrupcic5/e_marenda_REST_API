package com.teapot.emarenda.domain.lunch_attendance.service;

import com.teapot.emarenda.domain.lunch_attendance.dto.LunchAttendanceDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LunchAttendanceService {
    List<LunchAttendanceDto> findAllLunchAttendance();
    List<LunchAttendanceDto> findByStudentId(Long studentId);
    List<LunchAttendanceDto> findByDate(LocalDate date);
    Optional<LunchAttendanceDto> findByStudentIdAndDate(Long studentId, LocalDate date);
    LunchAttendanceDto save(LunchAttendanceDto attendance);
    void deleteById(Long id);
}
