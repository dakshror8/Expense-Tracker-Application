package com.example.Expense.Tracker.repo;

import com.example.Expense.Tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
