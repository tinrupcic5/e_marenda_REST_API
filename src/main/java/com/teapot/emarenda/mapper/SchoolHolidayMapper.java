package com.teapot.emarenda.mapper;


import com.teapot.emarenda.domain.school_holiday.entity.SchoolHolidayEntity;
import com.teapot.emarenda.domain.school_holiday.model.SchoolHolidayModel;
import org.springframework.stereotype.Component;

@Component
public class SchoolHolidayMapper implements AbstractMapper<SchoolHolidayModel, SchoolHolidayEntity> {
    private final SchoolMapper schoolMapper;

    public SchoolHolidayMapper(SchoolMapper schoolMapper) {
        this.schoolMapper = schoolMapper;
    }

    @Override
    public SchoolHolidayModel toModel(SchoolHolidayEntity entity) {
        return new SchoolHolidayModel(
                entity.getId(),
                entity.getNonWorkingDate(),
                entity.getName(),
                entity.getDescription(),
                schoolMapper.toModel(entity.getSchool())
        );
    }

    @Override
    public SchoolHolidayEntity toEntity(SchoolHolidayModel model) {
        return new SchoolHolidayEntity(
                model.getId(),
                model.getNonWorkingDate(),
                model.getName(),
                model.getDescription(),
                schoolMapper.toEntity(model.getSchool())
        );
    }
}
