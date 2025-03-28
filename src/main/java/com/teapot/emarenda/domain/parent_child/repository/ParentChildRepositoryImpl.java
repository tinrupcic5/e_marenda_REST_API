package com.teapot.emarenda.domain.parent_child.repository;

import com.teapot.emarenda.domain.parent_child.entity.ParentChildEntity;
import com.teapot.emarenda.domain.parent_child.model.ParentChildModel;
import com.teapot.emarenda.mapper.ParentChildMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class ParentChildRepositoryImpl implements ParentChildRepository {
    private final ParentChildJpaRepository parentChildRepository;
    private final ParentChildMapper parentChildMapper;

    public ParentChildRepositoryImpl(ParentChildJpaRepository parentChildRepository, ParentChildMapper parentChildMapper) {
        this.parentChildRepository = parentChildRepository;
        this.parentChildMapper = parentChildMapper;
    }

    @Override
    public Set<ParentChildModel> findByParentId(long parentId) {
        return parentChildRepository.findByParentId(parentId)
                .stream()
                .map(parentChildMapper::toModel)
                .collect(Collectors.toSet());
    }

    @Override
    @Transactional
    public ParentChildEntity save(ParentChildModel parentChildModel) {
        return parentChildRepository.save(parentChildMapper.toEntity(parentChildModel));
    }
}
