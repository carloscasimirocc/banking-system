package com.banking.banking_system.model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.banking.banking_system.enums.OperationStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity 
@Table(name = "financial_operations") 
public class FinancialOperation {

    @Id 
    @GeneratedValue(strategy=GenerationType.UUID)
    private UUID id;

    @Column (nullable = false)
    private String reference;

    @Column (nullable = false, unique = true, updatable = false)
    private String idempotencyKey;

    @Column (nullable = false)
    private BigDecimal amount;

    @Enumerated (EnumType.STRING)
    @Column(nullable = false)
    private OperationStatus status;
    
    private String failureReason;
    
    @CreationTimestamp 
    @Column (nullable = false)
    private Instant createdAt;

    private Long reversalof;


    
    public FinancialOperation(){}

    public UUID getId() {
        return id;
    }

    public String getReference() {
        return reference;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public OperationStatus getStatus() {
        return status;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Long getReversalof() {
        return reversalof;
    }

    


}
