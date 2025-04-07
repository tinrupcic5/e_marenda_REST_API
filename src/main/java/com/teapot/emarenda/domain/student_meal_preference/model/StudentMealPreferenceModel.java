package com.teapot.emarenda.domain.student_meal_preference.model;

import java.time.LocalDate;

public class StudentMealPreferenceModel {
    private Long id;
    private Long studentId;
    private LocalDate date;
    private boolean hasPreference;
    private String preferenceType;
    private String notes;

    public StudentMealPreferenceModel() {
    }

    public StudentMealPreferenceModel(Long id, Long studentId, LocalDate date, boolean hasPreference, String preferenceType, String notes) {
        this.id = id;
        this.studentId = studentId;
        this.date = date;
        this.hasPreference = hasPreference;
        this.preferenceType = preferenceType;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public boolean isHasPreference() {
        return hasPreference;
    }

    public void setHasPreference(boolean hasPreference) {
        this.hasPreference = hasPreference;
    }

    public String getPreferenceType() {
        return preferenceType;
    }

    public void setPreferenceType(String preferenceType) {
        this.preferenceType = preferenceType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
} 