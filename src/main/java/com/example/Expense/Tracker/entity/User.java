package com.example.Expense.Tracker.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            nullable = false,
            updatable = false
    )
    private Long id;
    @Column(
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;
    @Column(
          unique = true,
          nullable = false
    )
    private String email;

    @OneToMany(       // inverse side
            mappedBy = "user",
            cascade = {CascadeType.MERGE,CascadeType.PERSIST},
            orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<>();

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void addTransaction(Transaction newTransaction){
        this.transactions.add(newTransaction);
        newTransaction.setUser(this);
    }

    public void removeTransaction(Transaction transaction){
        this.transactions.remove(transaction);
        transaction.setUser(null);
    }
}
