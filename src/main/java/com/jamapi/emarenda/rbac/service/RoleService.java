package com.jamapi.emarenda.rbac.service;

import com.jamapi.emarenda.rbac.entity.RoleEntity;

import java.util.Optional;

public interface RoleService {
  Optional<RoleEntity> findByName(String name);
}
