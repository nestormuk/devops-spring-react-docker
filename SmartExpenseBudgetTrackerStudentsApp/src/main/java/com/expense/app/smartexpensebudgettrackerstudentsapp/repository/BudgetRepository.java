package com.expense.app.smartexpensebudgettrackerstudentsapp.repository;

import com.expense.app.smartexpensebudgettrackerstudentsapp.model.Budget;
import com.expense.app.smartexpensebudgettrackerstudentsapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findByUser(User user);
}