package com.jamapi.emarenda.rbac.repository;

import com.jamapi.emarenda.rbac.entity.UserEntity;
import com.jamapi.emarenda.rbac.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Long> {
  Optional<UserEntity> findByUsername(String username);

  Optional<UserEntity> findUserEntityByOib(String oib);

  @Query("SELECT e FROM UserEntity e WHERE e.grade is not NULL")
  Set<UserEntity> findAllWhereUserIsStudent();
}
