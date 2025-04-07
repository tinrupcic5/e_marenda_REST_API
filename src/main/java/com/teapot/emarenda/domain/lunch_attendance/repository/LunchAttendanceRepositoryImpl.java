package com.teapot.emarenda.domain.lunch_attendance.repository;

import com.teapot.emarenda.domain.lunch_attendance.entity.LunchAttendanceEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class LunchAttendanceRepositoryImpl implements LunchAttendanceRepository {
    private final LunchAttendanceJpaRepository jpaRepository;

    public LunchAttendanceRepositoryImpl(LunchAttendanceJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<LunchAttendanceEntity> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public List<LunchAttendanceEntity> findByStudentId(Long studentId) {
        return jpaRepository.findByUserEntityId(studentId);
    }

    @Override
    public List<LunchAttendanceEntity> findByDate(LocalDate date) {
        return jpaRepository.findByLunchDate(date);
    }

    @Override
    public Optional<LunchAttendanceEntity> findByStudentIdAndDate(Long studentId, LocalDate date) {
        return jpaRepository.findByUserEntityIdAndLunchDate(studentId, date);
    }

    @Override
    @Transactional
    public LunchAttendanceEntity save(LunchAttendanceEntity attendance) {
        return jpaRepository.save(attendance);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}
