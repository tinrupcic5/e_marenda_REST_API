package com.jamapi.emarenda.domain.lunch_day.model;

import com.jamapi.emarenda.domain.school_holiday.entity.SchoolHolidayEntity;
import com.jamapi.emarenda.domain.school_holiday.model.SchoolHolidayModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LunchDayModel {
    private Long id;
    private LocalDate lunchDayDate;
    private String description;
    private Boolean isHoliday;
    private SchoolHolidayModel schoolHoliday;
}

