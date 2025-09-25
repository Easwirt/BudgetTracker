package com.budget.tracker.budgettracker.controllers;

import com.budget.tracker.budgettracker.dto.CreateAccountRequest;
import com.budget.tracker.budgettracker.persistance.model.Account;
import com.budget.tracker.budgettracker.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AccountController {
    
    @Autowired
    private AccountService accountService;
    
    @PostMapping("/account")
    public ResponseEntity<Account> createAccount(@Valid @RequestBody CreateAccountRequest request) {
        Account createdAccount = accountService.createAccount(request);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }
}