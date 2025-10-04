package com.example.Expense.Tracker.mapper;

import com.example.Expense.Tracker.dto.AddTransactionRequestDto;
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
    public static Transaction toTransaction(AddTransactionRequestDto e){
        return new Transaction(e.getName(),
                e.getAmount(),
                e.getCategory(),
                e.getType());
    }
    public static AddTransactionRequestDto toAddNewExpenseDto(Transaction e){
        return new AddTransactionRequestDto(e.getName(),
                e.getType(),
                e.getAmount(),
                e.getCategory());
    }
}
