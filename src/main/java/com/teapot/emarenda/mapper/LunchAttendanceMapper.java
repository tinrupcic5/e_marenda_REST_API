package com.teapot.emarenda.mapper;

import com.teapot.emarenda.domain.lunch_attendance.dto.LunchAttendanceDto;
import com.teapot.emarenda.domain.lunch_attendance.entity.LunchAttendanceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LunchAttendanceMapper {
    @Mapping(source = "userEntity.id", target = "user.id")
    @Mapping(source = "lunchDate", target = "lunchDate")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "comment", target = "comment")
    LunchAttendanceDto toDto(LunchAttendanceEntity entity);

    @Mapping(source = "user.id", target = "userEntity.id")
    @Mapping(source = "lunchDate", target = "lunchDate")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "comment", target = "comment")
    LunchAttendanceEntity toEntity(LunchAttendanceDto dto);

    List<LunchAttendanceDto> toDtoList(List<LunchAttendanceEntity> entities);
    List<LunchAttendanceEntity> toEntityList(List<LunchAttendanceDto> dtos);
}
