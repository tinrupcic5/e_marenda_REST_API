package com.jamapi.emarenda.domain.monthly_receipt.entity;

import com.jamapi.emarenda.domain.monthly_receipt.ReceiptStatus;
import com.jamapi.emarenda.domain.school.entity.SchoolEntity;
import com.jamapi.emarenda.rbac.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "monthly_receipt")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "receipt_date", nullable = false)
    private LocalDate receiptDate;

    @Column(name = "for_month", nullable = false)
    private LocalDate forMonth;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private UserEntity createdBy;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReceiptStatus status;
}
