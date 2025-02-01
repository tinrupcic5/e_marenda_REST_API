package com.jamapi.emarenda.domain.user_activity.service;

import com.jamapi.emarenda.domain.user_activity.model.UserActivityModel;
import com.jamapi.emarenda.domain.user_activity.repository.UserActivityRepository;
import com.jamapi.emarenda.mapper.UserActivityMapper;
import com.jamapi.emarenda.rbac.entity.UserEntity;
import com.jamapi.emarenda.rbac.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserActivityServiceImpl implements UserActivityService {
    private final UserActivityRepository userActivityRepository;
    private final UserActivityMapper mapper;
    private final UserService userService;

    public UserActivityServiceImpl(UserActivityRepository userActivityRepository, UserActivityMapper mapper, UserService userService) {
        this.userActivityRepository = userActivityRepository;
        this.mapper = mapper;
        this.userService = userService;
    }

    @Override
    public void logUserActivity(String actionType, String description) {
        UserActivityModel userActivityModel = new UserActivityModel();
        UserEntity currentUser = userService.getCurrentUser();

        userActivityModel.setUserId(currentUser.getId());
        userActivityModel.setActionType(actionType);
        userActivityModel.setActionDescription(description);

        userActivityRepository.saveUserActivity(mapper.toEntity(userActivityModel));
    }
}
