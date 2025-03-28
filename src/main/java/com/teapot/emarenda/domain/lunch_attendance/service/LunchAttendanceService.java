package com.teapot.emarenda.domain.lunch_attendance.service;

import com.teapot.emarenda.domain.lunch_attendance.model.LunchAttendanceDto;

public interface LunchAttendanceService {
    String saveLunchAttendance(LunchAttendanceDto lunchAttendanceDto);
}
