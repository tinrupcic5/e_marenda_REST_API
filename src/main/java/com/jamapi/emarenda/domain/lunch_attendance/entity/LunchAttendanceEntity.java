package com.jamapi.emarenda.domain.lunch_attendance.entity;

import com.jamapi.emarenda.rbac.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "lunch_attendance")
@AllArgsConstructor
@NoArgsConstructor
public class LunchAttendanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity userEntity;

    @Column(name = "lunch_date", nullable = false)
    private LocalDate lunchDate;

    @Column(nullable = false)
    private Boolean status;

    private String comment;
}
