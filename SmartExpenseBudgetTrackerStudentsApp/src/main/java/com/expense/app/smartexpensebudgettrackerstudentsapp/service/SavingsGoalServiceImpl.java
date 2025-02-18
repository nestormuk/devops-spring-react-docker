package com.expense.app.smartexpensebudgettrackerstudentsapp.service;

import com.expense.app.smartexpensebudgettrackerstudentsapp.model.SavingsGoal;
import com.expense.app.smartexpensebudgettrackerstudentsapp.model.User;
import com.expense.app.smartexpensebudgettrackerstudentsapp.repository.SavingsGoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SavingsGoalServiceImpl implements SavingsGoalService{

    private final SavingsGoalRepository savingsGoalRepository;

    @Override
    public SavingsGoal createGoal(User user, String name, Double targetAmount) {
        SavingsGoal goal = new SavingsGoal(null, name, targetAmount, 0.0, user);
        return savingsGoalRepository.save(goal);
    }

    @Override
    public List<SavingsGoal> getUserGoals(User user) {
        return savingsGoalRepository.findByUser(user);
    }

    @Override
    public Optional<SavingsGoal> getGoalById(Long id) {
        return savingsGoalRepository.findById(id);
    }

    @Override
    public boolean deleteGoal(Long id) {
        if (savingsGoalRepository.existsById(id)) {
            savingsGoalRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
