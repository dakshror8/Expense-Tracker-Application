package com.example.Expense.Tracker;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class ExpenseConfiguration {

    @Bean
    CommandLineRunner commandLineRunner(ExpenseRepository repository){
        return args -> {
                    Expense powerBill = new Expense("power bill", 3000L);

                    Expense rent = new Expense("house rent",18000L);

                    repository.saveAll(List.of(powerBill,rent));

        };
    }
}
