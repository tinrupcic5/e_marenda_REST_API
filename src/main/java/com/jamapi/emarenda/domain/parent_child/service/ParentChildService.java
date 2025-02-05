package com.jamapi.emarenda.domain.parent_child.service;

import com.jamapi.emarenda.domain.parent_child.entity.ParentChildEntity;

import java.util.Set;

public interface ParentChildService {
    Set<ParentChildEntity> findByParentId(long parentId);

}
