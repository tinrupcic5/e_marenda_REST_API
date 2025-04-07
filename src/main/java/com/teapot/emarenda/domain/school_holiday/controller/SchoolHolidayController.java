package com.teapot.emarenda.domain.school_holiday.controller;

import com.teapot.emarenda.domain.school_holiday.model.SchoolHolidayModel;
import com.teapot.emarenda.domain.school_holiday.service.SchoolHolidayService;
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
@RequestMapping("/api/school-holidays")
@Tag(name = "School Holiday Management", description = "APIs for managing school holidays and non-working days")
@SecurityRequirement(name = "bearerAuth")
public class SchoolHolidayController {

    private final SchoolHolidayService schoolHolidayService;

    public SchoolHolidayController(SchoolHolidayService schoolHolidayService) {
        this.schoolHolidayService = schoolHolidayService;
    }

    @GetMapping
    @Operation(summary = "Get all school holidays", description = "Retrieves a list of all school holidays")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved school holidays"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<List<SchoolHolidayModel>> getAllHolidays() {
        return ResponseEntity.ok(schoolHolidayService.findAll());
    }

    @GetMapping("/{date}")
    @Operation(summary = "Get holiday by date", description = "Retrieves a school holiday by its date")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the holiday"),
        @ApiResponse(responseCode = "404", description = "Holiday not found"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER')")
    public ResponseEntity<SchoolHolidayModel> getHolidayByDate(
            @Parameter(description = "Date of the holiday", required = true)
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return schoolHolidayService.findByNonWorkingDate(date)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new holiday", description = "Creates a new school holiday")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Holiday created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SchoolHolidayModel> createHoliday(
            @Parameter(description = "School holiday details", required = true)
            @RequestBody SchoolHolidayModel holiday) {
        return ResponseEntity.ok(schoolHolidayService.save(holiday));
    }

    @DeleteMapping("/{date}")
    @Operation(summary = "Delete a holiday", description = "Deletes a school holiday by its date")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Holiday deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Holiday not found"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteHoliday(
            @Parameter(description = "Date of the holiday to delete", required = true)
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        schoolHolidayService.deleteByDate(date);
        return ResponseEntity.noContent().build();
    }
} 