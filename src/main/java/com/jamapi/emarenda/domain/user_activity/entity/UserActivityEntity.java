package com.jamapi.emarenda.domain.user_activity.entity;

import com.jamapi.emarenda.rbac.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_activity")
@Data
public class UserActivityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "action_type", length = 50, nullable = false)
    private String actionType;

    @Column(name = "action_description", length = 255)
    private String actionDescription;

    @Column(name = "action_timestamp", nullable = false, updatable = false)
    private LocalDateTime actionTimestamp = LocalDateTime.now();
}
