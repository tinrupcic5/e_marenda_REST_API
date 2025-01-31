package com.jamapi.emarenda.domain.user_activity.repository;

import com.jamapi.emarenda.domain.user_activity.entity.UserActivityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActivityJpaRepository extends JpaRepository<UserActivityEntity, Long> {
}
