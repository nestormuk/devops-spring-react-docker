package com.expense.app.smartexpensebudgettrackerstudentsapp.service;

import com.expense.app.smartexpensebudgettrackerstudentsapp.model.Expense;
import com.expense.app.smartexpensebudgettrackerstudentsapp.model.User;

import java.util.List;
import java.util.Optional;

public interface ExpenseService {
    Expense addExpense(User user, Expense expense);
    List<Expense> getUserExpenses(User user);
    Optional<Expense> getExpenseById(Long id);
    boolean deleteExpense(Long id);
}