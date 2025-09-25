package com.budget.tracker.budgettracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public class CreateAccountRequest {
    @NotNull(message = "User ID is required")
    private Long userId;
    
    @NotBlank(message = "Account name is required")
    private String name;
    
    @NotNull(message = "Balance is required")
    @PositiveOrZero(message = "Balance must be positive or zero")
    private BigDecimal balance;
    
    public CreateAccountRequest() {}
    
    public CreateAccountRequest(Long userId, String name, BigDecimal balance) {
        this.userId = userId;
        this.name = name;
        this.balance = balance;
    }
    
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
}