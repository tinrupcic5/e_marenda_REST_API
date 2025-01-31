package com.jamapi.emarenda.domain.user_activity.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserActivityModel {
    private Long userId;
    private String actionType;
    private String actionDescription;
}
