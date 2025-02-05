package com.jamapi.emarenda.domain.parent_child.repository;

import com.jamapi.emarenda.domain.parent_child.entity.ParentChildEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public class ParentChildRepositoryImpl implements ParentChildRepository {
    private final ParentChildJpaRepository parentChildRepository;

    public ParentChildRepositoryImpl(ParentChildJpaRepository parentChildRepository) {
        this.parentChildRepository = parentChildRepository;
    }

    @Override
    public Set<ParentChildEntity> findByParentId(long parentId) {
        return parentChildRepository.findByParentId(parentId);
    }
}
