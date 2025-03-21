package com.jamapi.emarenda.rbac.service;

import com.jamapi.emarenda.rbac.entity.UserEntity;
import com.jamapi.emarenda.rbac.model.UserDto;
import com.jamapi.emarenda.rbac.model.UserModel;

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
