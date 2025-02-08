package com.jamapi.emarenda.mapper;

import com.jamapi.emarenda.domain.lunch_attendance.entity.LunchAttendanceEntity;
import com.jamapi.emarenda.domain.lunch_attendance.model.LunchAttendanceModel;
import org.springframework.stereotype.Component;

@Component
public class LunchAttendanceMapper implements AbstractMapper<LunchAttendanceModel, LunchAttendanceEntity> {
    private final UserMapper userMapper;
    private final LunchDayMapper lunchDayMapper;

    public LunchAttendanceMapper(UserMapper userMapper, LunchDayMapper lunchDayMapper) {
        this.userMapper = userMapper;
        this.lunchDayMapper = lunchDayMapper;
    }

    @Override
    public LunchAttendanceModel toModel(LunchAttendanceEntity entity) {
        return new LunchAttendanceModel(
                entity.getId(),
                userMapper.toModel(entity.getUserEntity()),
                lunchDayMapper.toModel(entity.getLunchDayEntity()),
                entity.getStatus(),
                entity.getComment()
        );
    }

    @Override
    public LunchAttendanceEntity toEntity(LunchAttendanceModel model) {
        return new LunchAttendanceEntity(
                model.getId(),
                userMapper.toEntity(model.getUser()),
                lunchDayMapper.toEntity(model.getLunchDay()),
                model.getStatus(),
                model.getComment()
        );
    }
}
