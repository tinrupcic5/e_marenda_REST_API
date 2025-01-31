package com.jamapi.emarenda.domain.user_activity.repository;

import com.jamapi.emarenda.domain.user_activity.entity.UserActivityEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UserActivityRepositoryImpl implements UserActivityRepository {
    private final UserActivityJpaRepository userActivityJpaRepository;

    public UserActivityRepositoryImpl(UserActivityJpaRepository userActivityJpaRepository) {
        this.userActivityJpaRepository = userActivityJpaRepository;
    }

    @Override
    public void saveUserActivity(UserActivityEntity userActivityEntity) {
        userActivityJpaRepository.save(userActivityEntity);
    }
}
