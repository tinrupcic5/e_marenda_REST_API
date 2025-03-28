package com.teapot.emarenda.domain.user_activity;

public interface UserActivityService {
    void logUserActivity(String actionType, String actionDescription);
}
