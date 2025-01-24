package com.jamapi.emarenda.domain.grade.repository;

import com.jamapi.emarenda.domain.grade.entity.GradeEntity;
import com.jamapi.emarenda.domain.school.entity.SchoolEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeJpaRepository extends JpaRepository<GradeEntity, Long> {

}

