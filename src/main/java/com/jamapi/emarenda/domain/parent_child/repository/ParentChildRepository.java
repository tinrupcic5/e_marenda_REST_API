package com.jamapi.emarenda.domain.parent_child.repository;

import com.jamapi.emarenda.domain.parent_child.entity.ParentChildEntity;
import com.jamapi.emarenda.domain.parent_child.model.ParentChildModel;

import java.util.Set;

public interface ParentChildRepository {
    Set<ParentChildModel> findByParentId(long parentId);
    ParentChildEntity save(ParentChildModel parentChildModel);

}
