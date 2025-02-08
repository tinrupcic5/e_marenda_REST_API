package com.jamapi.emarenda.domain.lunch_day.entity;

import com.jamapi.emarenda.domain.school_holiday.entity.SchoolHolidayEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "lunch_day")
@AllArgsConstructor
@NoArgsConstructor
public class LunchDayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "lunch_day_date", nullable = false, unique = true)
    private LocalDate lunchDayDate;

    private String description;

    @Column(name = "is_holiday", nullable = false)
    private Boolean isHoliday;

    @ManyToOne
    @JoinColumn(name = "lunch_day_date", referencedColumnName = "non_working_date", insertable = false, updatable = false)
    private SchoolHolidayEntity schoolHolidayEntity;

}
