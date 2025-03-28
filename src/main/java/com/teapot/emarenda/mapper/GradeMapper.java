package com.teapot.emarenda.mapper;


import com.teapot.emarenda.domain.grade.entity.GradeEntity;
import com.teapot.emarenda.domain.grade.model.GradeModel;
import org.springframework.stereotype.Component;

@Component
public class GradeMapper implements AbstractMapper<GradeModel, GradeEntity> {


    @Override
    public GradeModel toModel(GradeEntity entity) {
        if (entity == null) return null;
        return new GradeModel(entity.getId(), entity.getName());
    }

    @Override
    public GradeEntity toEntity(GradeModel model) {
        return new GradeEntity(model.getId(), model.getName());
    }
}
