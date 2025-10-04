package com.budget.tracker.budgettracker.persistance.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "BT_TRANSACTION")
@Data
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;
    
    @Column(name = "account_id", nullable = false)
    private Long accountId;
    
    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "category")
    private String category;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private TransactionType type;
    
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    public Transaction(Long accountId, BigDecimal amount, String description, String category, TransactionType type) {
        this.accountId = accountId;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.type = type;
        this.createdAt = LocalDateTime.now();
    }
}