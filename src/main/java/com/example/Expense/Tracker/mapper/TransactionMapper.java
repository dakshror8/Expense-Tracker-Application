package com.example.Expense.Tracker.mapper;

import com.example.Expense.Tracker.dto.TransactionRequestDto;
import com.example.Expense.Tracker.dto.TransactionDto;
import com.example.Expense.Tracker.entity.Transaction;

public class TransactionMapper {
    public static Transaction toTransaction(TransactionDto e){
        return new Transaction(e.getName(),
                e.getAmount(),
                e.getCategory(),
                e.getType());
    }
    public static TransactionDto toTransactionDto(Transaction e){
        return new TransactionDto(e.getId(),
                e.getName(),
                e.getType(),
                e.getAmount(),
                e.getCategory());
    }
    public static Transaction toTransaction(TransactionRequestDto e){
        return new Transaction(e.getName(),
                e.getAmount(),
                e.getCategory(),
                e.getType());
    }
    public static TransactionRequestDto toAddNewExpenseDto(Transaction e){
        return new TransactionRequestDto(e.getName(),
                e.getType(),
                e.getAmount(),
                e.getCategory());
    }
}
