package com.jamapi.emarenda.rbac.repository;

import com.jamapi.emarenda.rbac.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
  UserEntity findByUsername(String username);
}
