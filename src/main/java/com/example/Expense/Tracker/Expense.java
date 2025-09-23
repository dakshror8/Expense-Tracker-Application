package com.example.Expense.Tracker;

import jakarta.persistence.*;

import java.util.Objects;

import static jakarta.persistence.GenerationType.*;

@Entity
@Table(
        name = "expense"
)
public class Expense {
    @Id
    @SequenceGenerator(
            name = "expense_sequence",
            sequenceName = "expense_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "expense_sequence"
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
            name = "Amount",
            nullable = false
    )
    private Long amount;

    public Expense() {
    }

    public Expense(String name, Long amount) {
        this.name = name;
        this.amount = amount;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                '}';
    }
}
