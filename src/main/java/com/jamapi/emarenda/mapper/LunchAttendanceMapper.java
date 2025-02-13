package com.jamapi.emarenda.mapper;

import com.jamapi.emarenda.domain.lunch_attendance.entity.LunchAttendanceEntity;
import com.jamapi.emarenda.domain.lunch_attendance.model.LunchAttendanceDto;
import org.springframework.stereotype.Component;

@Component
public class LunchAttendanceMapper implements AbstractMapper<LunchAttendanceDto, LunchAttendanceEntity> {
    private final UserMapper userMapper;

    public LunchAttendanceMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public LunchAttendanceDto toModel(LunchAttendanceEntity entity) {
        return new LunchAttendanceDto(
                entity.getId(),
                userMapper.toModel(entity.getUserEntity()),
                entity.getLunchDate(),
                entity.getStatus(),
                entity.getComment()
        );
    }

    @Override
    public LunchAttendanceEntity toEntity(LunchAttendanceDto model) {
        return new LunchAttendanceEntity(
                model.getId(),
                userMapper.toEntity(model.getUser()),
                model.getLunchDate(),
                model.getStatus(),
                model.getComment()
        );
    }
}
