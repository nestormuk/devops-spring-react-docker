package com.expense.app.smartexpensebudgettrackerstudentsapp.service;

import com.expense.app.smartexpensebudgettrackerstudentsapp.model.SavingsGoal;
import com.expense.app.smartexpensebudgettrackerstudentsapp.model.User;

import java.util.List;
import java.util.Optional;

public interface SavingsGoalService {

    SavingsGoal createGoal(User user, String name, Double targetAmount);
    List<SavingsGoal> getUserGoals(User user);
    Optional<SavingsGoal> getGoalById(Long id);
    boolean deleteGoal(Long id);
}
