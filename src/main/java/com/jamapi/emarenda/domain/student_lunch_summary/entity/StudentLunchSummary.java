package com.jamapi.emarenda.domain.student_lunch_summary.entity;

import com.jamapi.emarenda.rbac.entity.UserEntity;
import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@Entity
@Table(name = "student_lunch_summary")
public class StudentLunchSummary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(nullable = false)
    private Integer month;

    @Column(nullable = false)
    private Integer year;

    @Column(name = "total_cost", nullable = false)
    private BigDecimal totalCost;
}