package com.jamapi.emarenda.domain.parent_child.repository;

import com.jamapi.emarenda.domain.parent_child.entity.ParentChildEntity;

import java.util.Optional;
import java.util.Set;

public interface ParentChildRepository {
    Set<ParentChildEntity> findByParentId(long parentId);
}
