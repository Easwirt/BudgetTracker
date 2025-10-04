package com.budget.tracker.budgettracker.service;

import com.budget.tracker.budgettracker.dto.CreateAccountRequest;
import com.budget.tracker.budgettracker.persistance.model.Account;
import com.budget.tracker.budgettracker.persistance.model.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    public Account createAccount(CreateAccountRequest request) {
        Account account = new Account();
        account.setUserId(request.getUserId());
        account.setName(request.getName());
        account.setBalance(request.getBalance());
        account.setCreatedAt(LocalDateTime.now());
        
        return accountRepository.save(account);
    }
    
    @Transactional
    public void updateBalance(Long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new RuntimeException("Account not found with id: " + accountId));
        
        BigDecimal newBalance = account.getBalance().add(amount);
        account.setBalance(newBalance);
        accountRepository.save(account);
    }
}