package com.jamapi.emarenda.rbac.model;

import com.jamapi.emarenda.rbac.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class UserCsvDto {

  private String username;
  private String password;
  private String email;
  private String phone;
  private String name;
  private String lastName;
  private String oib;
  private Set<String> roles;
  private long schoolId;
  private long gradeId;
  private Set<String> childOib;
}
