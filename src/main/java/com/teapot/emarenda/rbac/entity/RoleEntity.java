package com.teapot.emarenda.rbac.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.media.Schema;

@NoArgsConstructor
@Data
@Entity
@Table(name = "app_role")
@Schema(description = "Role entity representing user roles in the system")
public class RoleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Schema(description = "Unique identifier for the role")
  private long id;

  @Column 
  @Schema(description = "Name of the role (e.g., ADMIN, STUDENT, TEACHER)")
  private String name;

  @Column 
  @Schema(description = "Description of the role and its permissions")
  private String description;
}
