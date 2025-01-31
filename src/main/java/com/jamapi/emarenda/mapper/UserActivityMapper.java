package com.jamapi.emarenda.mapper;


import com.jamapi.emarenda.domain.user_activity.entity.UserActivityEntity;
import com.jamapi.emarenda.domain.user_activity.model.UserActivityModel;
import com.jamapi.emarenda.rbac.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserActivityMapper implements AbstractMapper<UserActivityModel, UserActivityEntity> {

    @Override
    public UserActivityModel toModel(UserActivityEntity entity) {
        if (entity == null) {
            return null;
        }
        UserActivityModel model = new UserActivityModel();
        model.setUserId(entity.getUser().getId());
        model.setActionType(entity.getActionType());
        model.setActionDescription(entity.getActionDescription());
        return model;
    }

    @Override
    public UserActivityEntity toEntity(UserActivityModel model) {
        if (model == null) {
            return null;
        }
        UserActivityEntity entity = new UserActivityEntity();
        UserEntity user = new UserEntity();
        user.setId(model.getUserId());
        entity.setUser(user);
        entity.setActionType(model.getActionType());
        entity.setActionDescription(model.getActionDescription());
        entity.setActionTimestamp(LocalDateTime.now());

        return entity;
    }
}
