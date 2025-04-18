package com.teapot.emarenda.domain.lunch_attendance.scheduler;

import com.teapot.emarenda.domain.lunch_attendance.dto.LunchAttendanceDto;
import com.teapot.emarenda.domain.lunch_attendance.service.LunchAttendanceService;
import com.teapot.emarenda.rbac.service.UserService;
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
            lunchAttendanceService.save(lunchAttendanceDto);
        });

    }
}
