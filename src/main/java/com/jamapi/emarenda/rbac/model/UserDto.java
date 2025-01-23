package com.jamapi.emarenda.rbac.model;

import com.jamapi.emarenda.rbac.entity.UserEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDto {

  private String username;
  private String password;
  private String email;
  private String phone;
  private String name;

  public UserEntity getUserFromDto() {
    UserEntity userEntity = new UserEntity();
    userEntity.setUsername(username);
    userEntity.setPassword(password);
    userEntity.setEmail(email);
    userEntity.setPhone(phone);
    userEntity.setName(name);

    return userEntity;
  }
}
