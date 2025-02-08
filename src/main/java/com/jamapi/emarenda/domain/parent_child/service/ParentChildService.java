package com.jamapi.emarenda.domain.parent_child.service;

import com.jamapi.emarenda.domain.parent_child.model.ParentChildModel;

import java.util.Set;

public interface ParentChildService {
    Set<ParentChildModel> findByParentId(long parentId);
    String saveRelationship(ParentChildModel parentChildModel);
}
