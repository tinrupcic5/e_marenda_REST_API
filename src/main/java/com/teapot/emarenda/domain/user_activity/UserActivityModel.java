package com.teapot.emarenda.domain.user_activity;

import com.teapot.emarenda.rbac.model.UserModel;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserActivityModel {
    private UserModel user;
    private String actionType;
    private String actionDescription;
    private LocalDateTime actionTimestamp;

    @Override
    public String toString() {
        return  "user=" + user +
                ", actionType= " + actionType +
                ", actionDescription= " + actionDescription +
                ", actionTimestamp= " + actionTimestamp;
    }
}
