package com.teapot.emarenda.rbac.service;

import com.teapot.emarenda.rbac.entity.UserEntity;
import com.teapot.emarenda.rbac.model.UserDto;
import com.teapot.emarenda.rbac.model.UserModel;

import java.util.List;
import java.util.Set;

public interface UserService {

  String saveUser(UserDto user);

  List<UserEntity> findAll();

  UserEntity findByUsername(String username);

  UserEntity getCurrentUser();

  UserModel findByOib(String oib);

  Set<UserModel> findAllWhereUserIsStudent();

}
