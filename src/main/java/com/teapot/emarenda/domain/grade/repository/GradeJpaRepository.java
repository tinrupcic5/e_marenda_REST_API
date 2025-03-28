package com.teapot.emarenda.domain.grade.repository;

import com.teapot.emarenda.domain.grade.entity.GradeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeJpaRepository extends JpaRepository<GradeEntity, Long> {

}

