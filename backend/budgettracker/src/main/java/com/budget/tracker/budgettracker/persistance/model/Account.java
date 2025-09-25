package com.budget.tracker.budgettracker.persistance.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "BT_ACCOUNT")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "account_id")
    private Long accountId;
    
    // @Column(name = "user_id", nullable = false)
    private Long userId;
    
    // @Column(name = "name", nullable = false)
    private String name;
    
    // @Column(name = "balance", nullable = false, precision = 10, scale = 2)
    private BigDecimal balance;
    
    // @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    
    public Account() {

    }
    
    public Account(Long userId, String name, BigDecimal balance) {
        this.userId = userId;
        this.name = name;
        this.balance = balance;
        this.createdAt = LocalDateTime.now();
    }
    
    public Long getAccountId() { 
        return accountId; 
    }

    public void setAccountId(Long accountId) { 
        this.accountId = accountId; 
    }
    
    public Long getUserId() { 
        return userId; 
    }

    public void setUserId(Long userId) { 
        this.userId = userId; 
    }
    
    public String getName() { 
        return name; 
    }

    public void setName(String name) { 
        this.name = name; 
    }
    
    public BigDecimal getBalance() { 
        return balance; 
    }

    public void setBalance(BigDecimal balance) { 
        this.balance = balance; 
    }
    
    public LocalDateTime getCreatedAt() { 
        return createdAt; 
    }

    public void setCreatedAt(LocalDateTime createdAt) { 
        this.createdAt = createdAt; 
    }
}