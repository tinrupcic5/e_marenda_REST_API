package com.teapot.emarenda.mapper;


import com.teapot.emarenda.domain.school.entity.SchoolEntity;
import com.teapot.emarenda.domain.school.model.SchoolModel;
import org.springframework.stereotype.Component;

@Component
public class SchoolMapper implements AbstractMapper<SchoolModel, SchoolEntity> {


    @Override
    public SchoolModel toModel(SchoolEntity entity) {
        if (entity == null) return null;
        return new SchoolModel(
                entity.getId(),
                entity.getName(),
                entity.getCity(),
                entity.getAddress(),
                entity.getOib());
    }

    @Override
    public SchoolEntity toEntity(SchoolModel model) {
        return new SchoolEntity(
                model.getId(),
                model.getName(),
                model.getCity(),
                model.getAddress(),
                model.getOib()
        );
    }
}
