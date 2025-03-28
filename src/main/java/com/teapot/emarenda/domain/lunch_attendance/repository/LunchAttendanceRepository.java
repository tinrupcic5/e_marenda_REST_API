package com.teapot.emarenda.domain.lunch_attendance.repository;

import com.teapot.emarenda.domain.lunch_attendance.entity.LunchAttendanceEntity;
import com.teapot.emarenda.domain.lunch_attendance.model.LunchAttendanceDto;

public interface LunchAttendanceRepository {

    LunchAttendanceEntity saveLunchAttendance(LunchAttendanceDto lunchAttendanceDto);
}
