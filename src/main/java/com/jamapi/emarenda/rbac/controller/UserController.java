package com.jamapi.emarenda.rbac.controller;

import com.jamapi.emarenda.domain.response_message.ResponseMessage;
import com.jamapi.emarenda.domain.user_activity.ActionType;
import com.jamapi.emarenda.domain.user_activity.service.UserActivityService;
import com.jamapi.emarenda.rbac.config.TokenBlacklistService;
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

    private final UserActivityService userActivityService;

    private final TokenBlacklistService tokenBlacklistService;

    public UserController(
            AuthenticationManager authenticationManager,
            TokenProvider jwtTokenUtil,
            UserService userService, UserActivityService userActivityService, TokenBlacklistService tokenBlacklistService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
        this.userActivityService = userActivityService;
        this.tokenBlacklistService = tokenBlacklistService;
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

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/logout")
    public ResponseEntity<ResponseMessage> logout(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            String jwt = token.substring(7);
            tokenBlacklistService.blacklistToken(jwt.trim());
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        userActivityService.logUserActivity(ActionType.LOGOUT.name(), username);
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK, "User logged out successfully."));
    }


    @PostMapping("/register")
    public ResponseEntity<ResponseMessage> register(@RequestBody UserDto user) {
        userActivityService.logUserActivity(ActionType.REGISTER_STUDENT.name(), user.getUsername());
        return getUserEntityResponseEntity(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createUser(@RequestBody UserDto user) {
        userActivityService.logUserActivity(ActionType.CREATE_USER.name(), user.getUsername());
        return getUserEntityResponseEntity(user);
    }

    private ResponseEntity<ResponseMessage> getUserEntityResponseEntity(UserDto user) {
        var message = userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpStatus.CREATED,message));
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
