package com.budget.tracker.budgettracker.service;

import com.budget.tracker.budgettracker.dto.CreateAccountRequest;
import com.budget.tracker.budgettracker.persistance.model.Account;
import com.budget.tracker.budgettracker.persistance.model.repository.AccountRepository;
import com.budget.tracker.budgettracker.service.AccountService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AccountServiceTest {
    
    @Mock
    private AccountRepository accountRepository;
    
    @InjectMocks
    private AccountService accountService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testCreateAccount() {
        CreateAccountRequest request = new CreateAccountRequest(1L, "Savings", new BigDecimal("1000.00"));
        Account savedAccount = new Account(1L, "Savings", new BigDecimal("1000.00"));
        savedAccount.setAccountId(1L);
        
        when(accountRepository.save(any(Account.class))).thenReturn(savedAccount);

        Account result = accountService.createAccount(request);

        assertNotNull(result);
        assertEquals(1L, result.getAccountId());
        assertEquals(1L, result.getUserId());
        assertEquals("Savings", result.getName());
        assertEquals(new BigDecimal("1000.00"), result.getBalance());
        assertNotNull(result.getCreatedAt());
    }
}