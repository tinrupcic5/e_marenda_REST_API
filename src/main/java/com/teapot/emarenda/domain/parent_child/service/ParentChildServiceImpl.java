package com.teapot.emarenda.domain.parent_child.service;

import com.teapot.emarenda.domain.parent_child.model.ParentChildModel;
import com.teapot.emarenda.domain.parent_child.repository.ParentChildRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ParentChildServiceImpl implements ParentChildService {
 private final ParentChildRepository parentChildRepository;

    public ParentChildServiceImpl(ParentChildRepository parentChildRepository) {
        this.parentChildRepository = parentChildRepository;
    }

    @Override
    public Set<ParentChildModel> findByParentId(long parentId) {
        return parentChildRepository.findByParentId(parentId);
    }

    @Override
    public void saveRelationship(ParentChildModel parentChildModel) {
         parentChildRepository.save(parentChildModel);
    }
}
