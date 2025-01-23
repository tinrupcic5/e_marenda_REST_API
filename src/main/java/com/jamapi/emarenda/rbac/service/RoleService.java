package com.jamapi.emarenda.rbac.service;

import com.jamapi.emarenda.rbac.entity.RoleEntity;

public interface RoleService {
  RoleEntity findByName(String name);
}
