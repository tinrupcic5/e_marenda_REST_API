package com.teapot.emarenda.rbac.service.impl;

import com.teapot.emarenda.domain.grade.entity.GradeEntity;
import com.teapot.emarenda.domain.grade.service.GradeService;
import com.teapot.emarenda.domain.school.entity.SchoolEntity;
import com.teapot.emarenda.domain.school.service.SchoolService;
import com.teapot.emarenda.exception.UserNotFoundException;
import com.teapot.emarenda.mapper.UserMapper;
import com.teapot.emarenda.rbac.entity.RoleEntity;
import com.teapot.emarenda.rbac.entity.UserEntity;
import com.teapot.emarenda.rbac.model.UserDto;
import com.teapot.emarenda.rbac.model.UserModel;
import com.teapot.emarenda.rbac.repository.UserJpaRepository;
import com.teapot.emarenda.rbac.service.RoleService;
import com.teapot.emarenda.rbac.service.UserService;

import java.util.*;
import java.util.stream.Collectors;

import com.teapot.emarenda.rbac.validator.UserDtoValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(UserServiceImpl.class);

    private final RoleService roleService;

    private final SchoolService schoolService;

    private final GradeService gradeService;

    private final UserJpaRepository userJpaRepository;

    private final BCryptPasswordEncoder bcryptEncoder;

    private final UserMapper userMapper;

    private final UserDtoValidator userDtoValidator;

    public UserServiceImpl(
            RoleService roleService, SchoolService schoolService, GradeService gradeService, UserJpaRepository userJpaRepository, BCryptPasswordEncoder bcryptEncoder, UserMapper userMapper, UserDtoValidator userDtoValidator) {
        this.roleService = roleService;
        this.schoolService = schoolService;
        this.gradeService = gradeService;
        this.userJpaRepository = userJpaRepository;
        this.bcryptEncoder = bcryptEncoder;
        this.userMapper = userMapper;
        this.userDtoValidator = userDtoValidator;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userJpaRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));

        return new User(
                user.getUsername(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserEntity userEntity) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        userEntity.getRoleEntities()
                .forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        return authorities;
    }

    public List<UserEntity> findAll() {
        List<UserEntity> list = new ArrayList<>();
        userJpaRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userJpaRepository.findByUsername(username).orElseThrow();
    }

    @Override
    public String saveUser(UserDto user) {
        LOGGER.info("Saving user: {}", user.getUsername());

        UserEntity userEntity = userJpaRepository.findUserEntityByOib(user.getOib()).orElse(null);

        UserEntity nUserEntity = (userEntity != null) ? userEntity : user.getUserFromDto();

        nUserEntity.setPassword(bcryptEncoder.encode(user.getPassword()));

        Set<RoleEntity> roleEntitySet = user.getRoles().stream()
                .map(roleName -> roleService.findByName(roleName)
                        .orElseThrow(() -> new NoSuchElementException("Role not found: " + roleName)))
                .collect(Collectors.toSet());

        SchoolEntity school = (user.getSchoolId() == 0) ? null :
                schoolService.findById(user.getSchoolId())
                        .orElseThrow(() -> new NoSuchElementException("School not found with ID: " + user.getSchoolId()));

        user.accept(userDtoValidator);

        GradeEntity grade = gradeService.findById(user.getGradeId()).orElse(null);

        nUserEntity.setRoleEntities(roleEntitySet);
        nUserEntity.setSchool(school);
        nUserEntity.setGrade(grade);

        userJpaRepository.save(nUserEntity);

        LOGGER.info("User {}: {}", (userEntity != null) ? "updated" : "created", nUserEntity.getUsername());
        return "User " + ((userEntity != null) ? "updated: " : "created: ") + nUserEntity.getUsername();
    }


    @Override
    public UserEntity getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No authenticated user found");
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            Optional<UserEntity> user = userJpaRepository.findByUsername(userDetails.getUsername());
            return user.orElseThrow(() -> new IllegalArgumentException("User not found"));
        }

        throw new IllegalStateException("Unable to fetch user details");
    }

    @Override
    public UserModel findByOib(String oib) {
        return userMapper.toModel(
                userJpaRepository.findUserEntityByOib(oib)
                        .orElseThrow(() -> new UserNotFoundException("User with OIB " + oib + " not found"))
        );  }

    @Override
    public Set<UserModel> findAllWhereUserIsStudent() {
        return userJpaRepository.findAllWhereUserIsStudent()
                .stream()
                .map(userMapper::toModel)
                .collect(Collectors.toSet());
    }
}
