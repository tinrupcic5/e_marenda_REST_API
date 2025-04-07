package com.teapot.emarenda.domain.lunch_attendance.controller;

import com.teapot.emarenda.domain.lunch_attendance.dto.LunchAttendanceDto;
import com.teapot.emarenda.domain.lunch_attendance.service.LunchAttendanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/lunch-attendance")
@Tag(name = "Lunch Attendance Management", description = "APIs for managing student lunch attendance")
@SecurityRequirement(name = "bearerAuth")
public class LunchAttendanceController {

    private final LunchAttendanceService lunchAttendanceService;

    public LunchAttendanceController(LunchAttendanceService lunchAttendanceService) {
        this.lunchAttendanceService = lunchAttendanceService;
    }

    @GetMapping
    @Operation(summary = "Get all lunch attendance records", description = "Retrieves a list of all lunch attendance records")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved lunch attendance records"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'KITCHEN')")
    public ResponseEntity<List<LunchAttendanceDto>> getAllLunchAttendance() {
        return ResponseEntity.ok(lunchAttendanceService.findAllLunchAttendance());
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get attendance by student", description = "Retrieves lunch attendance records for a specific student")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the attendance records"),
        @ApiResponse(responseCode = "404", description = "Attendance records not found"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<List<LunchAttendanceDto>> getAttendanceByStudent(
            @Parameter(description = "Student ID", required = true)
            @PathVariable Long studentId) {
        return ResponseEntity.ok(lunchAttendanceService.findByStudentId(studentId));
    }

    @GetMapping("/date/{date}")
    @Operation(summary = "Get attendance by date", description = "Retrieves lunch attendance records for a specific date")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the attendance records"),
        @ApiResponse(responseCode = "404", description = "Attendance records not found"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<List<LunchAttendanceDto>> getAttendanceByDate(
            @Parameter(description = "Date", required = true)
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return ResponseEntity.ok(lunchAttendanceService.findByDate(date));
    }

    @GetMapping("/student/{studentId}/date/{date}")
    @Operation(summary = "Get attendance by student and date", description = "Retrieves a lunch attendance record for a specific student and date")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the attendance record"),
        @ApiResponse(responseCode = "404", description = "Attendance record not found"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    public ResponseEntity<LunchAttendanceDto> getAttendanceByStudentAndDate(
            @Parameter(description = "Student ID", required = true)
            @PathVariable Long studentId,
            @Parameter(description = "Date", required = true)
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return lunchAttendanceService.findByStudentIdAndDate(studentId, date)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new attendance record", description = "Creates a new lunch attendance record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Attendance record created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PreAuthorize("hasAnyRole('PARENT', 'STUDENT')")
    public ResponseEntity<LunchAttendanceDto> createAttendance(
            @Parameter(description = "Lunch attendance details", required = true)
            @RequestBody LunchAttendanceDto attendance) {
        return ResponseEntity.ok(lunchAttendanceService.save(attendance));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an attendance record", description = "Updates an existing lunch attendance record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Attendance record updated successfully"),
        @ApiResponse(responseCode = "404", description = "Attendance record not found"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PreAuthorize("hasAnyRole('PARENT', 'STUDENT')")
    public ResponseEntity<LunchAttendanceDto> updateAttendance(
            @Parameter(description = "Attendance ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated attendance details", required = true)
            @RequestBody LunchAttendanceDto attendance) {
        attendance.setId(id);
        return ResponseEntity.ok(lunchAttendanceService.save(attendance));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an attendance record", description = "Deletes a lunch attendance record")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Attendance record deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Attendance record not found"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PreAuthorize("hasAnyRole('STUDENT', 'PARENT')")
    public ResponseEntity<Void> deleteAttendance(
            @Parameter(description = "Attendance ID to delete", required = true)
            @PathVariable Long id) {
        lunchAttendanceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
