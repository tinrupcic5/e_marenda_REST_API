package com.jamapi.emarenda.mapper;


import com.jamapi.emarenda.domain.grade.entity.GradeEntity;
import com.jamapi.emarenda.domain.grade.model.GradeModel;
import com.jamapi.emarenda.domain.school.model.SchoolModel;
import com.jamapi.emarenda.rbac.entity.UserEntity;
import com.jamapi.emarenda.rbac.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class GradeMapper implements AbstractMapper<GradeModel, GradeEntity> {


    @Override
    public GradeModel toModel(GradeEntity entity) {
        return new GradeModel(entity.getId(), entity.getName());
    }

    @Override
    public GradeEntity toEntity(GradeModel model) {
        return new GradeEntity(model.getId(), model.getName());
    }
}
