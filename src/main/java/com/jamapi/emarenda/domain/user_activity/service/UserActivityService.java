package com.jamapi.emarenda.domain.user_activity.service;

public interface UserActivityService {
    void logUserActivity(String actionType, String description);
}
