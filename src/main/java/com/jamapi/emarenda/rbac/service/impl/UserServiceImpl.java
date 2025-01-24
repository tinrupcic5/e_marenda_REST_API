package com.jamapi.emarenda.rbac.service.impl;

import com.jamapi.emarenda.domain.grade.entity.GradeEntity;
import com.jamapi.emarenda.domain.grade.service.GradeService;
import com.jamapi.emarenda.domain.school.entity.SchoolEntity;
import com.jamapi.emarenda.domain.school.service.SchoolService;
import com.jamapi.emarenda.rbac.entity.RoleEntity;
import com.jamapi.emarenda.rbac.entity.UserEntity;
import com.jamapi.emarenda.rbac.model.UserDto;
import com.jamapi.emarenda.rbac.repository.UserJpaRepository;
import com.jamapi.emarenda.rbac.service.RoleService;
import com.jamapi.emarenda.rbac.service.UserService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    private final RoleService roleService;

    private final SchoolService schoolService;

    private final GradeService gradeService;

    private final UserJpaRepository userDao;

    private final BCryptPasswordEncoder bcryptEncoder;

    public UserServiceImpl(
            RoleService roleService, SchoolService schoolService, GradeService gradeService, UserJpaRepository userDao, BCryptPasswordEncoder bcryptEncoder) {
        this.roleService = roleService;
        this.schoolService = schoolService;
        this.gradeService = gradeService;
        this.userDao = userDao;
        this.bcryptEncoder = bcryptEncoder;
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userDao.findByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(
                userEntity.getUsername(), userEntity.getPassword(), getAuthority(userEntity));
    }

    private Set<SimpleGrantedAuthority> getAuthority(UserEntity userEntity) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        userEntity.getRoleEntities()
                .forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
        return authorities;
    }

    public List<UserEntity> findAll() {
        List<UserEntity> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public UserEntity findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public UserEntity save(UserDto user) {
        UserEntity nUserEntity = user.getUserFromDto();

        nUserEntity.setPassword(bcryptEncoder.encode(user.getPassword()));

        Set<RoleEntity> roleEntitySet = user.getRoles().stream()
                .map(roleService::findByName)
                .collect(Collectors.toSet());

        SchoolEntity school = schoolService.findById(user.getSchoolId());
        GradeEntity grade = gradeService.findById(user.getGradeId());

        nUserEntity.setRoleEntities(roleEntitySet);

        nUserEntity.setSchool(school);
        nUserEntity.setGrade(grade);

        return userDao.save(nUserEntity);
    }


}
