package com.example.Expense.Tracker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {
    private Long id;
    private String name;
    private String type;
    private Double amount;
    private String category;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;


}
