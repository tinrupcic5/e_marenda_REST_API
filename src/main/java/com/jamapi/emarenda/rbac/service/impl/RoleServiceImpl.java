package com.jamapi.emarenda.rbac.service.impl;

import com.jamapi.emarenda.rbac.entity.RoleEntity;
import com.jamapi.emarenda.rbac.repository.RoleRepository;
import com.jamapi.emarenda.rbac.service.RoleService;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleDao;

  public RoleServiceImpl(RoleRepository roleDao) {
    this.roleDao = roleDao;
  }

  @Override
  public RoleEntity findByName(String name) {
    return roleDao.findRoleByName(name);
  }
}
