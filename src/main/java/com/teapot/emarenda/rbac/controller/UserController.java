package com.teapot.emarenda.rbac.controller;

import com.teapot.emarenda.domain.response_message.ResponseMessage;
import com.teapot.emarenda.rbac.config.TokenBlacklistService;
import com.teapot.emarenda.rbac.config.TokenProvider;
import com.teapot.emarenda.rbac.entity.UserEntity;
import com.teapot.emarenda.rbac.model.AuthToken;
import com.teapot.emarenda.rbac.model.LoginUser;
import com.teapot.emarenda.rbac.model.UserDto;
import com.teapot.emarenda.rbac.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Tag(name = "User Management", description = "APIs for managing users, authentication, and authorization in the school lunch system")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private final AuthenticationManager authenticationManager;
    private final TokenProvider jwtTokenUtil;
    private final UserService userService;
    private final TokenBlacklistService tokenBlacklistService;

    public UserController(
            AuthenticationManager authenticationManager,
            TokenProvider jwtTokenUtil,
            UserService userService, TokenBlacklistService tokenBlacklistService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @Operation(
        summary = "Authenticate user",
        description = "Authenticates a user with their username and password, returning a JWT token for subsequent authenticated requests"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully authenticated",
            content = @Content(schema = @Schema(implementation = AuthToken.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Invalid credentials provided"
        )
    })
    @PostMapping("/authenticate")
    public ResponseEntity<AuthToken> login(
        @Parameter(description = "Login credentials containing username and password")
        @RequestBody LoginUser loginUser
    ) throws AuthenticationException {
        LOGGER.info("User attempting to log in: {}", loginUser.getUsername());
        final Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                loginUser.getUsername(), loginUser.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        LOGGER.info("User {} logged in successfully", loginUser.getUsername());
        return ResponseEntity.ok(new AuthToken(token));
    }

    @Operation(
        summary = "Logout user",
        description = "Logs out the current user by invalidating their JWT token. The token will be blacklisted and cannot be used for subsequent requests."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully logged out",
            content = @Content(schema = @Schema(implementation = ResponseMessage.class))
        ),
        @ApiResponse(
            responseCode = "401",
            description = "Not authenticated or invalid token"
        )
    })
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/logout")
    public ResponseEntity<ResponseMessage> logout(
        @Parameter(description = "Bearer token to invalidate")
        @RequestHeader("Authorization") String token
    ) {
        LOGGER.info("User attempting to log out");
        if (token != null && token.startsWith("Bearer ")) {
            String jwt = token.substring(7);
            tokenBlacklistService.blacklistToken(jwt.trim());
            LOGGER.info("Token {} has been blacklisted", jwt);
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        SecurityContextHolder.clearContext();
        LOGGER.info("User {} logged out successfully", username);
        return ResponseEntity.ok(new ResponseMessage(HttpStatus.OK, "User logged out successfully."));
    }

    @Operation(
        summary = "Create new user",
        description = "Creates a new user in the system with specified roles and permissions. This endpoint is restricted to administrators only."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "User created successfully",
            content = @Content(schema = @Schema(implementation = ResponseMessage.class))
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input data provided"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Not authorized - requires ADMIN role"
        )
    })
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<ResponseMessage> createUser(
        @Parameter(description = "User details including username, password, roles, and other information")
        @RequestBody UserDto user
    ) {
        LOGGER.info("Creating new user with username: {}", user.getUsername());
        return getUserEntityResponseEntity(user);
    }

    private ResponseEntity<ResponseMessage> getUserEntityResponseEntity(UserDto user) {
        var message = userService.saveUser(user);
        LOGGER.info("User {} created successfully", user.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpStatus.CREATED, message));
    }

    @Operation(
        summary = "Get all users",
        description = "Retrieves a list of all users in the system. This endpoint is restricted to administrators and kitchen staff."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved users",
            content = @Content(schema = @Schema(implementation = UserEntity.class))
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Not authorized - requires ADMIN or KITCHEN role"
        )
    })
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ADMIN') or hasRole('KITCHEN')")
    @GetMapping("/find/all")
    public List<UserEntity> getAllList() {
        LOGGER.info("Fetching all users");
        return userService.findAll();
    }

    @Operation(
        summary = "Find user by username",
        description = "Retrieves a specific user by their username. This endpoint is restricted to administrators and kitchen staff."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved user",
            content = @Content(schema = @Schema(implementation = UserEntity.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "User not found with the specified username"
        ),
        @ApiResponse(
            responseCode = "403",
            description = "Not authorized - requires ADMIN or KITCHEN role"
        )
    })
    @SecurityRequirement(name = "bearerAuth")
    @PreAuthorize("hasRole('ADMIN') or hasRole('KITCHEN')")
    @GetMapping("/find/by/username")
    public UserEntity findByUsername(
        @Parameter(description = "Username to search for")
        @RequestParam String username
    ) {
        LOGGER.info("Searching for user with username: {}", username);
        return userService.findByUsername(username);
    }
}
