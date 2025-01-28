package com.jamapi.emarenda.domain.lunch_day.repository;

import com.jamapi.emarenda.domain.lunch_day.entity.LunchDayEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LunchDayJpaRepository extends JpaRepository<LunchDayEntity, Long> {
}
