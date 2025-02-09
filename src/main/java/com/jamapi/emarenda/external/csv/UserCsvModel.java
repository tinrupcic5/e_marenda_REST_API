package com.jamapi.emarenda.external.csv;

import com.jamapi.emarenda.rbac.model.UserCsvDto;
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

//    username,password,email,phone,name,lastName,roles,schoolId,gradeId
//    john_doe,pass123,john@example.com,1234567890,John,Doe,ADMIN,USER,1,2
//    jane_doe,secure456,jane@example.com,0987654321,Jane,Doe,USER,2,3

    private Set<String> convertChild(String childOib) {
        return Arrays.stream(childOib.split(","))
                .map(String::trim)
                .collect(Collectors.toSet());
    }
}
