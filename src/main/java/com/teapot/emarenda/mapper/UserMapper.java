package com.teapot.emarenda.mapper;


import com.teapot.emarenda.rbac.entity.UserEntity;
import com.teapot.emarenda.rbac.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements AbstractMapper<UserModel, UserEntity> {

    private final SchoolMapper schoolMapper;
    private final GradeMapper gradeMapper;

    public UserMapper(SchoolMapper schoolMapper, GradeMapper gradeMapper) {
        this.schoolMapper = schoolMapper;
        this.gradeMapper = gradeMapper;
    }

    @Override
    public UserModel toModel(UserEntity entity) {
        return new UserModel(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getName(),
                entity.getLastName(),
                schoolMapper.toModel(entity.getSchool()),
                gradeMapper.toModel(entity.getGrade())
        );
    }

    @Override
    public UserEntity toEntity(UserModel model) {
        UserEntity entity = new UserEntity();
        entity.setId(model.getId());
        return entity;
    }
}
