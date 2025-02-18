package com.expense.app.smartexpensebudgettrackerstudentsapp.service;

import com.expense.app.smartexpensebudgettrackerstudentsapp.model.Budget;
import com.expense.app.smartexpensebudgettrackerstudentsapp.model.User;

public interface BudgetService {
    Budget setBudget(User user, Double amount, String period);
    boolean checkBudget(User user, Double expenseAmount);
}
