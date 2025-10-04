package com.example.Expense.Tracker.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import java.time.LocalDate;
import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.*;


@Entity
@Data
@NoArgsConstructor
@Table(
        name = "transaction"
)
public class Transaction {
    @Id
    @GeneratedValue(
            strategy = IDENTITY
    )
    @Column(
            name = "Id",
            nullable = false,
            updatable = false
    )
    private Long Id;

    @Column(
            name = "Name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(

            name = "type_of_transaction",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String type;

    @Column(
            name = "Amount",
            nullable = false
    )
    private Double amount;

    @Column(
            name = "Category",
            columnDefinition = "TEXT"
    )
    private String category;

    @CreationTimestamp
    @Column(
            name = "created_at"
    )
    private LocalDateTime created_at;

    @CreationTimestamp
    @Column(
            name = "updated_at"
    )
    private LocalDateTime updated_at;

    @ManyToOne
    @JoinColumn(nullable = false)  // owning side
    private User user;

    public Transaction(String name, Double amount, String category, String type) {
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.type = type;
    }


}
