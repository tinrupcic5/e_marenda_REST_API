package com.teapot.emarenda.domain.student_meal_preference.controller;

import com.teapot.emarenda.domain.student_meal_preference.model.StudentMealPreferenceModel;
import com.teapot.emarenda.domain.student_meal_preference.service.StudentMealPreferenceService;
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
@RequestMapping("/api/student-meal-preferences")
@Tag(name = "Student Meal Preference Management", description = "APIs for managing student meal preferences")
@SecurityRequirement(name = "bearerAuth")
public class StudentMealPreferenceController {

    private final StudentMealPreferenceService studentMealPreferenceService;

    public StudentMealPreferenceController(StudentMealPreferenceService studentMealPreferenceService) {
        this.studentMealPreferenceService = studentMealPreferenceService;
    }

    @GetMapping
    @Operation(summary = "Get all meal preferences", description = "Retrieves a list of all student meal preferences")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved meal preferences"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'PARENT')")
    public ResponseEntity<List<StudentMealPreferenceModel>> getAllPreferences() {
        return ResponseEntity.ok(studentMealPreferenceService.findAll());
    }

    @GetMapping("/student/{studentId}")
    @Operation(summary = "Get preferences by student", description = "Retrieves meal preferences for a specific student")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the preferences"),
        @ApiResponse(responseCode = "404", description = "Preferences not found"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'PARENT')")
    public ResponseEntity<List<StudentMealPreferenceModel>> getPreferencesByStudent(
            @Parameter(description = "Student ID", required = true)
            @PathVariable Long studentId) {
        return ResponseEntity.ok(studentMealPreferenceService.findByStudentId(studentId));
    }

    @GetMapping("/student/{studentId}/date/{date}")
    @Operation(summary = "Get preference by student and date", description = "Retrieves a meal preference for a specific student and date")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved the preference"),
        @ApiResponse(responseCode = "404", description = "Preference not found"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'TEACHER', 'PARENT')")
    public ResponseEntity<StudentMealPreferenceModel> getPreferenceByStudentAndDate(
            @Parameter(description = "Student ID", required = true)
            @PathVariable Long studentId,
            @Parameter(description = "Date", required = true)
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return studentMealPreferenceService.findByStudentIdAndDate(studentId, date)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new preference", description = "Creates a new student meal preference")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Preference created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'PARENT')")
    public ResponseEntity<StudentMealPreferenceModel> createPreference(
            @Parameter(description = "Student meal preference details", required = true)
            @RequestBody StudentMealPreferenceModel preference) {
        return ResponseEntity.ok(studentMealPreferenceService.save(preference));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a preference", description = "Updates an existing student meal preference")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Preference updated successfully"),
        @ApiResponse(responseCode = "404", description = "Preference not found"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'PARENT')")
    public ResponseEntity<StudentMealPreferenceModel> updatePreference(
            @Parameter(description = "Preference ID", required = true)
            @PathVariable Long id,
            @Parameter(description = "Updated preference details", required = true)
            @RequestBody StudentMealPreferenceModel preference) {
        preference.setId(id);
        return ResponseEntity.ok(studentMealPreferenceService.save(preference));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a preference", description = "Deletes a student meal preference")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Preference deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Preference not found"),
        @ApiResponse(responseCode = "403", description = "Access denied")
    })
    @PreAuthorize("hasAnyRole('ADMIN', 'PARENT')")
    public ResponseEntity<Void> deletePreference(
            @Parameter(description = "Preference ID to delete", required = true)
            @PathVariable Long id) {
        studentMealPreferenceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
} 