package com.jamapi.emarenda.rbac.controller;

import com.jamapi.emarenda.rbac.config.TokenProvider;
import com.jamapi.emarenda.rbac.entity.UserEntity;
import com.jamapi.emarenda.rbac.model.AuthToken;
import com.jamapi.emarenda.rbac.model.LoginUser;
import com.jamapi.emarenda.rbac.model.UserDto;
import com.jamapi.emarenda.rbac.service.UserService;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/users")
public class UserController {

  private final AuthenticationManager authenticationManager;

  private final TokenProvider jwtTokenUtil;

  private final UserService userService;

  public UserController(
      AuthenticationManager authenticationManager,
      TokenProvider jwtTokenUtil,
      UserService userService) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenUtil = jwtTokenUtil;
    this.userService = userService;
  }

  @PostMapping("/authenticate")
  public ResponseEntity<AuthToken> login(@RequestBody LoginUser loginUser)
      throws AuthenticationException {
    final Authentication authentication =
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginUser.getUsername(), loginUser.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    final String token = jwtTokenUtil.generateToken(authentication);
    return ResponseEntity.ok(new AuthToken(token));
  }

  @PostMapping("/register")
  public ResponseEntity<UserEntity> register(@RequestBody UserDto user) {
    return getUserEntityResponseEntity(user);
  }

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping("/create")
  public ResponseEntity<UserEntity> createUser(@RequestBody UserDto user) {
    return getUserEntityResponseEntity(user);
  }

  private ResponseEntity<UserEntity> getUserEntityResponseEntity(UserDto user) {
    UserEntity savedUser = userService.save(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
  }


  @PreAuthorize("hasRole('ADMIN') or hasRole('KITCHEN')")
  @GetMapping("/find/all")
  public List<UserEntity> getAllList() {
    return userService.findAll();
  }

  @PreAuthorize("hasRole('ADMIN') or hasRole('KITCHEN')")
  @GetMapping("/find/by/username")
  public UserEntity findByUsername(@RequestParam String username) {
    return userService.findByUsername(username);
  }
}
