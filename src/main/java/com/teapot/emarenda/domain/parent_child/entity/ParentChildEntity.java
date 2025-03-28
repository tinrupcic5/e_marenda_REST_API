package com.teapot.emarenda.domain.parent_child.entity;

import com.teapot.emarenda.rbac.entity.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.Serializable;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "parent_child")
@Schema(description = "Entity representing the relationship between parents and their children in the system")
public class ParentChildEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the parent-child relationship")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parent_id", nullable = false)
    @Schema(description = "Parent user in the relationship")
    private UserEntity parent;

    @ManyToOne
    @JoinColumn(name = "child_id", nullable = false)
    @Schema(description = "Child user in the relationship")
    private UserEntity child;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParentChildEntity that = (ParentChildEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "ParentChildEntity{" +
                "id=" + id +
                ", parent=" + parent +
                ", child=" + child +
                '}';
    }

    public ParentChildEntity(UserEntity parent, UserEntity child) {
        this.parent = parent;
        this.child = child;
    }
}
