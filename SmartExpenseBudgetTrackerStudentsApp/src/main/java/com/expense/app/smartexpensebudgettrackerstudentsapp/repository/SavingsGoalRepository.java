package com.expense.app.smartexpensebudgettrackerstudentsapp.repository;

import com.expense.app.smartexpensebudgettrackerstudentsapp.model.SavingsGoal;
import com.expense.app.smartexpensebudgettrackerstudentsapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SavingsGoalRepository extends JpaRepository<SavingsGoal, Long> {
    List<SavingsGoal> findByUser(User user);
}
