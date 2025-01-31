package com.jamapi.emarenda.domain.user_activity.repository;

import com.jamapi.emarenda.domain.user_activity.entity.UserActivityEntity;

public interface UserActivityRepository {
    void saveUserActivity(UserActivityEntity userActivityEntity);
}
