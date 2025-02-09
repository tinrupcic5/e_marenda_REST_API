package com.jamapi.emarenda.rbac.repository;

import com.jamapi.emarenda.rbac.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleJpaRepository extends JpaRepository<RoleEntity, Long> {
  Optional<RoleEntity> findRoleByName(String name);
}
