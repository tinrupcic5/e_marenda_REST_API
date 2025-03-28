package com.teapot.emarenda.domain.user_activity;

import com.teapot.emarenda.mapper.UserMapper;
import com.teapot.emarenda.rbac.model.UserModel;
import com.teapot.emarenda.rbac.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserActivityLoggerService implements UserActivityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserActivityLoggerService.class);
    private final UserService userService;
    private final UserMapper userMapper;

    public UserActivityLoggerService(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public void logUserActivity(String actionType, String actionDescription) {
        UserModel userModel = userMapper.toModel(userService.getCurrentUser());
        UserActivityModel userActivityModel = new UserActivityModel(userModel,actionType,actionDescription, LocalDateTime.now());
        LOGGER.info(userActivityModel.toString());
    }
}

