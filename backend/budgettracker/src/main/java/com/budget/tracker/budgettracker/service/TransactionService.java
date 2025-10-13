package com.budget.tracker.budgettracker.service;

import com.budget.tracker.budgettracker.dto.CreateTransactionRequest;
import com.budget.tracker.budgettracker.persistance.model.Transaction;
import com.budget.tracker.budgettracker.persistance.model.TransactionType;
import com.budget.tracker.budgettracker.persistance.model.repository.TransactionRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Autowired
    private AccountService accountService;
    
    @Transactional
    public Transaction createTransaction(CreateTransactionRequest request) {
        Transaction transaction = new Transaction(
            request.getAccountId(),
            request.getAmount(),
            request.getDescription(),
            request.getCategory(),
            request.getType()
        );
        
        if (request.getType() == TransactionType.INCOME) {
            accountService.updateBalance(request.getAccountId(), request.getAmount());
        } else {
            accountService.updateBalance(request.getAccountId(), request.getAmount().negate());
        }
        
        Transaction savedTransaction = transactionRepository.save(transaction);
        
        return savedTransaction;
    }

    public List<Transaction> getAllTransactionsByAccountId(Long accountId) {
        return transactionRepository.findAllByAccountId(accountId);
    }

    public Transaction getTransactionByAccountIdAndId(Long transactionId, Long accountId) {
        return transactionRepository.findByTransactionIdAndAccountId(transactionId, accountId);
    }
}