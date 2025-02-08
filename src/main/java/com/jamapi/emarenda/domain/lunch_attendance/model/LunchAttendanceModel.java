package com.jamapi.emarenda.domain.lunch_attendance.model;

import com.jamapi.emarenda.domain.lunch_day.entity.LunchDayEntity;
import com.jamapi.emarenda.domain.lunch_day.model.LunchDayModel;
import com.jamapi.emarenda.rbac.entity.UserEntity;
import com.jamapi.emarenda.rbac.model.UserModel;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LunchAttendanceModel {
    private Long id;
    private UserModel user;
    private LunchDayModel lunchDay;
    private Boolean status;
    private String comment;
}
