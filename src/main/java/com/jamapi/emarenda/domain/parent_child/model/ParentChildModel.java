package com.jamapi.emarenda.domain.parent_child.model;

import com.jamapi.emarenda.rbac.model.UserModel;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ParentChildModel {
    private UserModel parent;
    private UserModel child;
}
