package com.jamapi.emarenda.domain.lunch_attendance.scheduler;

import com.jamapi.emarenda.domain.lunch_attendance.model.LunchAttendanceDto;
import com.jamapi.emarenda.domain.lunch_attendance.service.LunchAttendanceService;
import com.jamapi.emarenda.rbac.service.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class LunchAttendanceScheduler {
    private final LunchAttendanceService lunchAttendanceService;
    private final UserService userService;

    public LunchAttendanceScheduler(LunchAttendanceService lunchAttendanceService, UserService userService) {
        this.lunchAttendanceService = lunchAttendanceService;
        this.userService = userService;
    }

    @Scheduled(cron = "${scheduler.cron.daily.job}")
    public void scheduleLunchAttendance() {
        var students = userService.findAllWhereUserIsStudent();
        students.forEach(student -> {
            LunchAttendanceDto lunchAttendanceDto = new LunchAttendanceDto();
            lunchAttendanceDto.setUser(student);
            lunchAttendanceDto.setStatus(true);
            lunchAttendanceDto.setLunchDate(LocalDate.now().plusDays(1));
            lunchAttendanceService.saveLunchAttendance(lunchAttendanceDto);
        });

    }
}
