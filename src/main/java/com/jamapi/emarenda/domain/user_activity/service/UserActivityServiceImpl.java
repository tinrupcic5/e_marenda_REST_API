package com.jamapi.emarenda.domain.user_activity.service;

import com.jamapi.emarenda.domain.user_activity.model.UserActivityModel;
import com.jamapi.emarenda.domain.user_activity.repository.UserActivityRepository;
import com.jamapi.emarenda.mapper.UserActivityMapper;
import org.springframework.stereotype.Service;

@Service
public class UserActivityServiceImpl implements UserActivityService {
    private final UserActivityRepository userActivityRepository;
    private final UserActivityMapper mapper;

    public UserActivityServiceImpl(UserActivityRepository userActivityRepository, UserActivityMapper mapper) {
        this.userActivityRepository = userActivityRepository;
        this.mapper = mapper;
    }

    @Override
    public void saveUserActivity(UserActivityModel userActivityModel) {
        userActivityRepository.saveUserActivity(mapper.toEntity(userActivityModel));
    }
}
