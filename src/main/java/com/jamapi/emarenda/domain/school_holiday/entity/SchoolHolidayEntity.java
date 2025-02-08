package com.jamapi.emarenda.domain.school_holiday.entity;

import com.jamapi.emarenda.domain.school.entity.SchoolEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "school_holiday")
@AllArgsConstructor
@NoArgsConstructor
public class SchoolHolidayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "non_working_date", nullable = false, unique = true)
    private LocalDate nonWorkingDate;

    @Column(nullable = false)
    private String name;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private SchoolEntity school;
}
