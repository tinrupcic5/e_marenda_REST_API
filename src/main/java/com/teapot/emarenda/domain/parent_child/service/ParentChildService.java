package com.teapot.emarenda.domain.parent_child.service;

import com.teapot.emarenda.domain.parent_child.model.ParentChildModel;

import java.util.Set;

public interface ParentChildService {
    Set<ParentChildModel> findByParentId(long parentId);
    void saveRelationship(ParentChildModel parentChildModel);
}
