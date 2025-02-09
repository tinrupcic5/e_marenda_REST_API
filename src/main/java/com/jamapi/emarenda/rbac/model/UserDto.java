package com.jamapi.emarenda.rbac.model;

import com.jamapi.emarenda.rbac.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserDto {

  private String username;
  private String password;
  private String email;
  private String phone;
  private String name;
  private String lastName;
  private String oib;
  private Set<String> roles;
  private Long schoolId;
  private Long gradeId;

  public UserEntity getUserFromDto() {
    UserEntity userEntity = new UserEntity();
    userEntity.setUsername(username);
    userEntity.setPassword(password);
    userEntity.setEmail(email);
    userEntity.setPhone(phone);
    userEntity.setName(name);
    userEntity.setLastName(lastName);
    userEntity.setOib(oib);

    return userEntity;
  }
}
