package com.teapot.emarenda.rbac.validator;

import com.teapot.emarenda.rbac.model.UserDto;

public interface InputDataVisitor<T extends UserDto> {
    default void visitUserRole(T userModel) {}

    default void accept(T userModel) {
        visitUserRole(userModel);
    }
}
