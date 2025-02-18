package com.expense.app.smartexpensebudgettrackerstudentsapp.repository;

import com.expense.app.smartexpensebudgettrackerstudentsapp.model.Expense;
import com.expense.app.smartexpensebudgettrackerstudentsapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUser(User user);
}