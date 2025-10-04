package com.example.Expense.Tracker.repo;

import com.example.Expense.Tracker.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    Optional<Transaction> findByIdAndUserId(Long id, Long userId);
}
