package com.jamapi.emarenda.domain.lunch_attendance.controller;

import com.jamapi.emarenda.domain.lunch_attendance.model.LunchAttendanceDto;
import com.jamapi.emarenda.domain.lunch_attendance.service.LunchAttendanceService;
import com.jamapi.emarenda.domain.response_message.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendance")
public class LunchAttendanceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LunchAttendanceController.class);

    private final LunchAttendanceService lunchAttendanceService;

    public LunchAttendanceController(LunchAttendanceService lunchAttendanceService) {
        this.lunchAttendanceService = lunchAttendanceService;
    }

    @PreAuthorize("hasRole('PARENT') or hasRole('STUDENT')")
    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createAttendance(@RequestBody LunchAttendanceDto lunchAttendanceDto) {
        LOGGER.info("Creating new attendance for user: {}", lunchAttendanceDto.getUser().getId());
        var saved = lunchAttendanceService.saveLunchAttendance(lunchAttendanceDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpStatus.CREATED, saved));
    }
}
