package com.teapot.emarenda.domain.school_holiday.model;

import com.teapot.emarenda.domain.school.model.SchoolModel;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchoolHolidayModel {
    private Long id;
    private LocalDate nonWorkingDate;
    private String name;
    private String description;
    private SchoolModel school;
}
