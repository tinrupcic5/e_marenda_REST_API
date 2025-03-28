package com.teapot.emarenda.rbac.service;

import com.teapot.emarenda.rbac.entity.RoleEntity;

import java.util.Optional;

public interface RoleService {
  Optional<RoleEntity> findByName(String name);
}
