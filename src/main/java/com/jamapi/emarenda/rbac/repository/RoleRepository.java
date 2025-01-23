package com.jamapi.emarenda.rbac.repository;

import com.jamapi.emarenda.rbac.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
  RoleEntity findRoleByName(String name);
}
