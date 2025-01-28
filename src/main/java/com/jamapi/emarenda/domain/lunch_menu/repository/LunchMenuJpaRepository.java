package com.jamapi.emarenda.domain.lunch_menu.repository;

import com.jamapi.emarenda.domain.lunch_menu.entity.LunchMenuEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LunchMenuJpaRepository extends JpaRepository<LunchMenuEntity, Long> {
}
