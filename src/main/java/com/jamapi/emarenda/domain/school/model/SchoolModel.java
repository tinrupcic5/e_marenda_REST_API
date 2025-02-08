package com.jamapi.emarenda.domain.school.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SchoolModel {
    private Long id;
    private String name;
    private String city;
    private String address;
    private String oib;
}
