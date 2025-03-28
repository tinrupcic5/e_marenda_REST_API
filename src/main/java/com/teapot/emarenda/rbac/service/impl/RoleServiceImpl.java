package com.teapot.emarenda.rbac.service.impl;

import com.teapot.emarenda.rbac.entity.RoleEntity;
import com.teapot.emarenda.rbac.repository.RoleJpaRepository;
import com.teapot.emarenda.rbac.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService {

  private final RoleJpaRepository roleDao;

  public RoleServiceImpl(RoleJpaRepository roleDao) {
    this.roleDao = roleDao;
  }

  @Override
  public Optional<RoleEntity> findByName(String name) {
    return roleDao.findRoleByName(name);
  }
}
