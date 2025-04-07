package com.teapot.emarenda.domain.lunch_attendance.service;

import com.teapot.emarenda.domain.lunch_attendance.dto.LunchAttendanceDto;
import com.teapot.emarenda.domain.lunch_attendance.repository.LunchAttendanceRepository;
import com.teapot.emarenda.mapper.LunchAttendanceMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LunchAttendanceServiceImpl implements LunchAttendanceService {
    private final LunchAttendanceRepository lunchAttendanceRepository;
    private final LunchAttendanceMapper lunchAttendanceMapper;

    public LunchAttendanceServiceImpl(LunchAttendanceRepository lunchAttendanceRepository, LunchAttendanceMapper lunchAttendanceMapper) {
        this.lunchAttendanceRepository = lunchAttendanceRepository;
        this.lunchAttendanceMapper = lunchAttendanceMapper;
    }

    @Override
    public List<LunchAttendanceDto> findAllLunchAttendance() {
        return lunchAttendanceMapper.toDtoList(lunchAttendanceRepository.findAll());
    }

    @Override
    public List<LunchAttendanceDto> findByStudentId(Long studentId) {
        return lunchAttendanceMapper.toDtoList(lunchAttendanceRepository.findByStudentId(studentId));
    }

    @Override
    public List<LunchAttendanceDto> findByDate(LocalDate date) {
        return lunchAttendanceMapper.toDtoList(lunchAttendanceRepository.findByDate(date));
    }

    @Override
    public Optional<LunchAttendanceDto> findByStudentIdAndDate(Long studentId, LocalDate date) {
        return lunchAttendanceRepository.findByStudentIdAndDate(studentId, date)
                .map(lunchAttendanceMapper::toDto);
    }

    @Override
    @Transactional
    public LunchAttendanceDto save(LunchAttendanceDto attendance) {
        return lunchAttendanceMapper.toDto(
                lunchAttendanceRepository.save(lunchAttendanceMapper.toEntity(attendance))
        );
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        lunchAttendanceRepository.deleteById(id);
    }

}
