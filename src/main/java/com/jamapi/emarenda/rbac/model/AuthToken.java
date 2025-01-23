package com.jamapi.emarenda.rbac.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AuthToken {
  private String token;

  public AuthToken(String token) {
    this.token = token;
  }
}
