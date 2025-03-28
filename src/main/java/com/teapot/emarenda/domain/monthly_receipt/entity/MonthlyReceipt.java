package com.teapot.emarenda.domain.monthly_receipt.entity;

import com.teapot.emarenda.domain.monthly_receipt.ReceiptStatus;
import com.teapot.emarenda.rbac.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "monthly_receipt")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Entity representing a monthly receipt for school lunches")
public class MonthlyReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier for the receipt")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @Schema(description = "User (student/parent) for whom this receipt is generated")
    private UserEntity user;

    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    @Schema(description = "Total amount due for the month (with 2 decimal places)")
    private BigDecimal amount;

    @Column(name = "receipt_date", nullable = false)
    @Schema(description = "Date when the receipt was generated")
    private LocalDate receiptDate;

    @Column(name = "for_month", nullable = false)
    @Schema(description = "Month for which this receipt is generated")
    private LocalDate forMonth;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    @Schema(description = "User who created this receipt")
    private UserEntity createdBy;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    @Schema(description = "Current status of the receipt (e.g., PENDING, PAID, CANCELLED)")
    private ReceiptStatus status;
}
