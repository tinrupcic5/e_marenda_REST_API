package com.jamapi.emarenda.mapper;


import com.jamapi.emarenda.domain.lunch_day.entity.LunchDayEntity;
import com.jamapi.emarenda.domain.lunch_day.model.LunchDayModel;
import com.jamapi.emarenda.domain.school_holiday.model.SchoolHolidayModel;
import com.jamapi.emarenda.rbac.entity.UserEntity;
import com.jamapi.emarenda.rbac.model.UserModel;
import org.springframework.stereotype.Component;

@Component
public class LunchDayMapper implements AbstractMapper<LunchDayModel, LunchDayEntity> {
    private final SchoolHolidayMapper schoolHolidayMapper;

    public LunchDayMapper(SchoolHolidayMapper schoolHolidayMapper) {
        this.schoolHolidayMapper = schoolHolidayMapper;
    }

    @Override
    public LunchDayModel toModel(LunchDayEntity entity) {
        return new LunchDayModel(
                entity.getId(),
                entity.getLunchDayDate(),
                entity.getDescription(),
                entity.getIsHoliday(),
                schoolHolidayMapper.toModel(entity.getSchoolHolidayEntity())
        );
    }

    @Override
    public LunchDayEntity toEntity(LunchDayModel model) {
        return new LunchDayEntity(
                model.getId(),
                model.getLunchDayDate(),
                model.getDescription(),
                model.getIsHoliday(),
                schoolHolidayMapper.toEntity(model.getSchoolHoliday())
        );
    }
}
