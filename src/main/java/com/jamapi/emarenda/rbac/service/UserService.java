package com.jamapi.emarenda.rbac.service;

import com.jamapi.emarenda.rbac.entity.UserEntity;
import com.jamapi.emarenda.rbac.model.UserDto;
import java.util.List;

public interface UserService {

  String saveUser(UserDto user);

  List<UserEntity> findAll();

  UserEntity findByUsername(String username);

  UserEntity getCurrentUser();
}
