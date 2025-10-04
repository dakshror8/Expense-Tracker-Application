package com.example.Expense.Tracker.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestDto {
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 40)
    private String name;

    @NotBlank(message = "Type of transaction required")
    private String type;

    @NotNull(message = "Amount required")
    @Min(value = 10, message = "Amount must be at least 10")
    private Double amount;

    private String category;
}
