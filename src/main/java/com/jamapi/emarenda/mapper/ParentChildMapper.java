package com.jamapi.emarenda.mapper;


import com.jamapi.emarenda.domain.parent_child.entity.ParentChildEntity;
import com.jamapi.emarenda.domain.parent_child.model.ParentChildModel;
import org.springframework.stereotype.Component;

@Component
public class ParentChildMapper implements AbstractMapper<ParentChildModel, ParentChildEntity> {
    private final UserMapper userMapper;

    public ParentChildMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public ParentChildModel toModel(ParentChildEntity parentChildEntity) {
        return new ParentChildModel(
                userMapper.toModel(parentChildEntity.getParent()),
                userMapper.toModel(parentChildEntity.getChild()));
    }

    @Override
    public ParentChildEntity toEntity(ParentChildModel model) {
        return new ParentChildEntity(
                userMapper.toEntity(model.getParent()),
                userMapper.toEntity(model.getChild())
        );
    }

}
