package com.teapot.emarenda.rbac.repository;

import com.teapot.emarenda.rbac.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long> {
  Optional<RoleEntity> findRoleByName(String name);
}
