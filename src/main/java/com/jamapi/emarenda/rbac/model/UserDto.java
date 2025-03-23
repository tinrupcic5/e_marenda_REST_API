package com.jamapi.emarenda.rbac.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jamapi.emarenda.external.csv.UserCsvModel;
import com.jamapi.emarenda.rbac.entity.UserEntity;
import com.jamapi.emarenda.rbac.validator.InputDataVisitor;
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

  @JsonIgnore
  public <T extends UserDto> void accept(InputDataVisitor<T> visitor) {
    visitor.accept((T) this);
  }
}
