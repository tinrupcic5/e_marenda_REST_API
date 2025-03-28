package com.teapot.emarenda.domain.lunch_attendance.entity;

import com.teapot.emarenda.rbac.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "lunch_attendance")
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Entity representing a student's lunch attendance record")
public class LunchAttendanceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the attendance record")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Schema(description = "Student for whom this attendance record is created")
    private UserEntity userEntity;

    @Column(name = "lunch_date", nullable = false)
    @Schema(description = "Date of the lunch attendance")
    private LocalDate lunchDate;

    @Column(nullable = false)
    @Schema(description = "Whether the student attended lunch (true) or not (false)")
    private Boolean status;

    @Schema(description = "Optional comment about the attendance")
    private String comment;
}
