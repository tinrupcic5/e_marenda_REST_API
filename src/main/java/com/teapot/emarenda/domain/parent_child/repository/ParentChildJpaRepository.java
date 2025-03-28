package com.teapot.emarenda.domain.parent_child.repository;

import com.teapot.emarenda.domain.parent_child.entity.ParentChildEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ParentChildJpaRepository extends JpaRepository<ParentChildEntity, Long> {
    Set<ParentChildEntity> findByParentId(Long parentId);
}

