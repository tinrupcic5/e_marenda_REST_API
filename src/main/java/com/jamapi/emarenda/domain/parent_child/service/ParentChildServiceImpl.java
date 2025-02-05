package com.jamapi.emarenda.domain.parent_child.service;

import com.jamapi.emarenda.domain.parent_child.entity.ParentChildEntity;
import com.jamapi.emarenda.domain.parent_child.repository.ParentChildRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ParentChildServiceImpl implements ParentChildService {
 private final ParentChildRepository parentChildRepository;

    public ParentChildServiceImpl(ParentChildRepository parentChildRepository) {
        this.parentChildRepository = parentChildRepository;
    }

    @Override
    public Set<ParentChildEntity> findByParentId(long parentId) {
        return parentChildRepository.findByParentId(parentId);
    }
}
