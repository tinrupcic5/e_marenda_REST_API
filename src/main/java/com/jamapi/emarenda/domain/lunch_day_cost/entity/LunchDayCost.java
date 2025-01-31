package com.jamapi.emarenda.domain.lunch_day_cost.entity;

import com.jamapi.emarenda.domain.school.entity.SchoolEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "lunch_day_cost")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LunchDayCost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private SchoolEntity school;

    @Column(name = "meal_cost", nullable = false, precision = 10, scale = 2)
    private BigDecimal mealCost;
}
