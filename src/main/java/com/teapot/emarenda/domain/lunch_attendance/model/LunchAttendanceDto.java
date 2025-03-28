package com.teapot.emarenda.domain.lunch_attendance.model;

import com.teapot.emarenda.rbac.model.UserModel;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LunchAttendanceDto {
    private Long id;
    private UserModel user;
    private LocalDate lunchDate;
    private Boolean status;
    private String comment;
}