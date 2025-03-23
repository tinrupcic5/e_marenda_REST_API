package com.jamapi.emarenda.external.csv;

import com.jamapi.emarenda.rbac.model.UserCsvDto;
import com.jamapi.emarenda.rbac.model.UserModel;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserCsvModel extends UserModel {

    @CsvBindByName(column = "username")
    private String username;

    @CsvBindByName(column = "password")
    private String password;

    @CsvBindByName(column = "email")
    private String email;

    @CsvBindByName(column = "phone")
    private String phone;

    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "lastName")
    private String lastName;

    @CsvBindByName(column = "oib")
    private String oib;

    @CsvBindByName(column = "role")
    private String role;

    @CsvBindByName(column = "schoolId")
    private Long schoolId;

    @CsvBindByName(column = "gradeId")
    private Long gradeId;

    @CsvBindByName(column = "childOib")
    private String childOib;

    public UserCsvDto toUserCsvDto() {
        return new UserCsvDto(
                this.username,
                this.password,
                this.email,
                this.phone,
                this.name,
                this.lastName,
                this.oib,
                Set.of(this.role),
                this.schoolId,
                this.gradeId,
                convertChild(this.childOib)
        );
    }

    private Set<String> convertChild(String childOib) {
        if (childOib == null || childOib.trim().isEmpty()) {
            return Set.of();
        }
        return Arrays.stream(childOib.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toSet());
    }

}
