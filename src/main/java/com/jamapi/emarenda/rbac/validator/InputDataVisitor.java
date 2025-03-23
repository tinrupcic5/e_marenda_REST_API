package com.jamapi.emarenda.rbac.validator;

import com.jamapi.emarenda.rbac.model.UserDto;

public interface InputDataVisitor<T extends UserDto> {
    default void visitUserRole(T userModel) {}

    default void accept(T userModel) {
        visitUserRole(userModel);
    }
}
