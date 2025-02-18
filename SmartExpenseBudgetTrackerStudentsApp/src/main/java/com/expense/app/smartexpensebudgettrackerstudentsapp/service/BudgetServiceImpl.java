package com.expense.app.smartexpensebudgettrackerstudentsapp.service;

import com.expense.app.smartexpensebudgettrackerstudentsapp.model.Budget;
import com.expense.app.smartexpensebudgettrackerstudentsapp.model.User;
import com.expense.app.smartexpensebudgettrackerstudentsapp.repository.BudgetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BudgetServiceImpl implements BudgetService{
    private final BudgetRepository budgetRepository;
    @Override
    public Budget setBudget(User user, Double amount, String period) {
        Budget budget = budgetRepository.findByUser(user)
                .orElse(new Budget(null, amount, period, user));
        budget.setAmount(amount);
        budget.setPeriod(period);
        return budgetRepository.save(budget);
    }

    @Override
    public boolean checkBudget(User user, Double expenseAmount) {
        Optional<Budget> budgetOpt = budgetRepository.findByUser(user);
        return budgetOpt.map(budget -> expenseAmount > budget.getAmount()).orElse(false);
    }
}
