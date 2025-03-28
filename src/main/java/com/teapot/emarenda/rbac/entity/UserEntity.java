package com.teapot.emarenda.rbac.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teapot.emarenda.domain.grade.entity.GradeEntity;
import com.teapot.emarenda.domain.school.entity.SchoolEntity;
import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
@Table(name = "app_user")
@Schema(description = "User entity representing a user in the system")
public class UserEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Unique identifier for the user")
  private long id;

  @Column 
  @Schema(description = "Username for login and identification")
  private String username;

  @Column 
  @JsonIgnore 
  @Schema(description = "Encrypted password for user authentication")
  private String password;

  @Column
  @Email(message = "Email should be valid")
  @Schema(description = "User's email address")
  private String email;

  @Column
  @Pattern(regexp = "^[0-9]{1,14}$", message = "Phone number should be no more than 14 digits")
  @Schema(description = "User's phone number (max 14 digits)")
  private String phone;

  @Column 
  @Schema(description = "User's first name")
  private String name;

  @Column 
  @Schema(description = "User's last name")
  private String lastName;

  @Column(nullable = false, unique = true)
  @Size(min = 11, max = 11, message = "OIB must be exactly 11 characters long")
  @Schema(description = "User's OIB (Personal Identification Number) - exactly 11 characters")
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
  @Schema(description = "Set of roles assigned to the user")
  private Set<RoleEntity> roleEntities;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "school_id", nullable = true)
  @Schema(description = "School associated with the user. Can be null for admin users.")
  private SchoolEntity school;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "grade_id")
  @Schema(description = "Grade associated with the user (if student)")
  private GradeEntity grade;

  @PrePersist
  @PreUpdate
  public void validateSchoolAssignment() {
    if (roleEntities != null) {
      boolean isAdmin = roleEntities.stream()
          .anyMatch(role -> "ADMIN".equals(role.getName()));
      if (isAdmin && school != null) {
        throw new IllegalStateException("Admin users cannot be assigned to a school");
      }
    }
  }
}
