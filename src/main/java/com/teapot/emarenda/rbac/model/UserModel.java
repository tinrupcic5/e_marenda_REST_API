package com.teapot.emarenda.rbac.model;

import com.teapot.emarenda.domain.grade.model.GradeModel;
import com.teapot.emarenda.domain.school.model.SchoolModel;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserModel {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private String name;
    private String lastName;
    private SchoolModel school;
    private GradeModel grade;
}
