package com.jamapi.emarenda.domain.lunch_attendance.repository;

import com.jamapi.emarenda.domain.lunch_attendance.entity.LunchAttendanceEntity;
import com.jamapi.emarenda.domain.lunch_attendance.model.LunchAttendanceDto;
import com.jamapi.emarenda.mapper.LunchAttendanceMapper;
import org.springframework.stereotype.Repository;

@Repository
public class LunchAttendanceRepositoryImpl implements LunchAttendanceRepository {
    private final LunchAttendanceJpaRepository jpaRepository;
    private final LunchAttendanceMapper mapper;

    public LunchAttendanceRepositoryImpl(LunchAttendanceJpaRepository jpaRepository, LunchAttendanceMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public LunchAttendanceEntity saveLunchAttendance(LunchAttendanceDto lunchAttendanceDto) {
        return jpaRepository.save(mapper.toEntity(lunchAttendanceDto));
    }
}
