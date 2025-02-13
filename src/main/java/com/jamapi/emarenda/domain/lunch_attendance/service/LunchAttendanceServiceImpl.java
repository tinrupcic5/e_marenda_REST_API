package com.jamapi.emarenda.domain.lunch_attendance.service;

import com.jamapi.emarenda.domain.lunch_attendance.model.LunchAttendanceDto;
import com.jamapi.emarenda.domain.lunch_attendance.repository.LunchAttendanceRepository;
import com.jamapi.emarenda.domain.school_holiday.model.SchoolHolidayModel;
import com.jamapi.emarenda.domain.school_holiday.service.SchoolHolidayService;
import com.jamapi.emarenda.mapper.LunchAttendanceMapper;
import com.jamapi.emarenda.utils.WeekendChecker;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LunchAttendanceServiceImpl implements LunchAttendanceService {
    private final LunchAttendanceRepository lunchAttendanceRepository;
    private final SchoolHolidayService schoolHolidayService;
    private final LunchAttendanceMapper lunchAttendanceMapper;

    public LunchAttendanceServiceImpl(LunchAttendanceRepository lunchAttendanceRepository, SchoolHolidayService schoolHolidayService, LunchAttendanceMapper lunchAttendanceMapper) {
        this.lunchAttendanceRepository = lunchAttendanceRepository;
        this.schoolHolidayService = schoolHolidayService;
        this.lunchAttendanceMapper = lunchAttendanceMapper;
    }

    @Override
    public String saveLunchAttendance(LunchAttendanceDto lunchAttendanceDto) {
        Optional<SchoolHolidayModel> schoolHolidayModel = schoolHolidayService.findByNonWorkingDate(lunchAttendanceDto.getLunchDate());
        if (schoolHolidayModel.isEmpty() && !WeekendChecker.isTomorrowWeekend()) {
            lunchAttendanceMapper.toModel(lunchAttendanceRepository.saveLunchAttendance(lunchAttendanceDto));
            return "Lunch save for date: " + lunchAttendanceDto.getLunchDate();
        } else {
            throw new IllegalStateException("Cannot save lunch attendance for day: " + lunchAttendanceDto.getLunchDate());
        }
    }

}
