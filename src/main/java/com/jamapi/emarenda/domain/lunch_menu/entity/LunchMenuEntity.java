package com.jamapi.emarenda.domain.lunch_menu.entity;

import com.jamapi.emarenda.domain.lunch_day.entity.LunchDayEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "lunch_menu")
public class LunchMenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "lunch_day_id", nullable = false)
    private LunchDayEntity lunchDayEntity;

    @Column(name = "meal_name")
    private String mealName;

    private String description;

}