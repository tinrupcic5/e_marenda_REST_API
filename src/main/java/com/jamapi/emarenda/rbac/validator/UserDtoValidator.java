package com.jamapi.emarenda.rbac.validator;

import com.jamapi.emarenda.exception.IPBadRequestException;
import com.jamapi.emarenda.rbac.entity.RolesEnum;
import com.jamapi.emarenda.rbac.model.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserDtoValidator implements InputDataVisitor<UserDto> {

    public static final Long INVALID_SCHOOL_ID = 0L;

    /**
     * Check if user is student before inserting into db from csv
     * @param userDto - get user role
     */
    @Override
    public void visitUserRole(UserDto userDto) {
        if (isStudent(userDto) &&  userDto.getSchoolId().equals(INVALID_SCHOOL_ID)) {
            throw new IPBadRequestException("Student " +
                    "'"+ userDto.getUsername() +"'" +
                    " cannot be inserted without school id.", null);
        }
    }

    private boolean isStudent(UserDto userModel) {
        return userModel.getRoles().stream()
                .anyMatch(role -> RolesEnum.STUDENT.name().equalsIgnoreCase(role));
    }
}
