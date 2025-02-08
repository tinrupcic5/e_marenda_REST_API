package com.jamapi.emarenda.external.csv;

import com.jamapi.emarenda.rbac.model.UserDto;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class UserCsvModel {

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
    private long schoolId;

    @CsvBindByName(column = "gradeId")
    private long gradeId;

    @CsvBindByName(column = "oib")
    private String childOib;

    public UserDto toUserDto() {
        return new UserDto(
                this.username,
                this.password,
                this.email,
                this.phone,
                this.name,
                this.lastName,
                this.oib,//TODO convert into set and save into relationship
                Set.of(this.role),
                this.schoolId,
                this.gradeId
        );
    }
    private Set<String> convertChild(String childOib) {
        return Arrays.stream(childOib.split(","))
                .map(String::trim)
                .collect(Collectors.toSet());
    }
}
