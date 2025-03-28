package com.teapot.emarenda.rbac.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LoginUser {
  private String username;
  private String password;
}
