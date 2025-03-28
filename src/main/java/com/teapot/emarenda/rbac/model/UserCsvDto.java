package com.teapot.emarenda.rbac.model;

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
  private Long schoolId;
  private Long gradeId;
  private Set<String> childOib;

  public UserDto toUserDto() {
    UserDto userDto = new UserDto();
    userDto.setUsername(username);
    userDto.setPassword(password);
    userDto.setEmail(email);
    userDto.setPhone(phone);
    userDto.setName(name);
    userDto.setLastName(lastName);
    userDto.setOib(oib);
    userDto.setRoles(roles);
    userDto.setSchoolId(schoolId);
    userDto.setGradeId(gradeId);
    return userDto;
  }
}
