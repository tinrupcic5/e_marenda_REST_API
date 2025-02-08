package com.jamapi.emarenda.rbac.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jamapi.emarenda.domain.grade.entity.GradeEntity;
import com.jamapi.emarenda.domain.school.entity.SchoolEntity;
import com.jamapi.emarenda.domain.user_activity.entity.UserActivityEntity;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "app_user")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column private String username;

  @Column @JsonIgnore private String password;

  @Column
  @Email(message = "Email should be valid")
  private String email;

  @Column
  @Pattern(regexp = "^[0-9]{1,14}$", message = "Phone number should be no more than 14 digits")
  private String phone;

  @Column private String name;

  @Column private String lastName;

  @Column(nullable = false, unique = true)
  private String oib;

  public void setName(String name) {
    if (name != null && !name.isEmpty()) {
      this.name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
  }
  public void setLastName(String lastName) {
    if (lastName != null && !lastName.isEmpty()) {
      this.lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
    }
  }

  @ManyToMany(
      fetch = FetchType.EAGER,
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  @JoinTable(
      name = "USER_ROLES",
      joinColumns = {@JoinColumn(name = "USER_ID")},
      inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
  private Set<RoleEntity> roleEntities;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "school_id")
  private SchoolEntity school;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "grade_id")
  private GradeEntity grade;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  @JsonIgnore
  private List<UserActivityEntity> activities;

}
