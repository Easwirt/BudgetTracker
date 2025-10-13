package com.budget.tracker.budgettracker.persistance.model.repository;

import com.budget.tracker.budgettracker.persistance.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountIdOrderByCreatedAtDesc(Long accountId);


    Transaction findByTransactionIdAndAccountId(Long transactionId, Long accountId);
}