package com.jamapi.emarenda.domain.school.repository;

import com.jamapi.emarenda.domain.school.entity.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolJpaRepository extends JpaRepository<SchoolEntity, Long> {

}

