package com.jamapi.emarenda.mapper;


import com.jamapi.emarenda.domain.lunch_day.entity.LunchDayEntity;
import com.jamapi.emarenda.domain.lunch_day.model.LunchDayModel;
import com.jamapi.emarenda.domain.school.model.SchoolModel;
import com.jamapi.emarenda.domain.school_holiday.entity.SchoolHolidayEntity;
import com.jamapi.emarenda.domain.school_holiday.model.SchoolHolidayModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
