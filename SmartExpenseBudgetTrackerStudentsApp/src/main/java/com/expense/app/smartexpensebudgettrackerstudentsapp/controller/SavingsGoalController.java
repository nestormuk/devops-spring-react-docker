package com.expense.app.smartexpensebudgettrackerstudentsapp.controller;

import com.expense.app.smartexpensebudgettrackerstudentsapp.model.SavingsGoal;
import com.expense.app.smartexpensebudgettrackerstudentsapp.model.User;
import com.expense.app.smartexpensebudgettrackerstudentsapp.repository.UserRepository;
import com.expense.app.smartexpensebudgettrackerstudentsapp.service.SavingsGoalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/goals")
@RequiredArgsConstructor
public class SavingsGoalController {

    private final SavingsGoalService savingsGoalService;
    private final UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createGoal(@RequestBody Map<String, Object> request) {
        Optional<User> user = userRepository.findByEmail((String) request.get("email"));
        if (user.isPresent()) {
            SavingsGoal goal = savingsGoalService.createGoal(user.get(), (String) request.get("name"), (Double) request.get("targetAmount"));
            return ResponseEntity.ok(goal);
        }
        return ResponseEntity.badRequest().body("User not found");
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<SavingsGoal>> getUserGoals(@PathVariable String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(value -> ResponseEntity.ok(savingsGoalService.getUserGoals(value)))
                .orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateGoal(@PathVariable Long id, @RequestBody SavingsGoal goalDetails) {
        Optional<SavingsGoal> goal = savingsGoalService.getGoalById(id);
        if (goal.isPresent()) {
            SavingsGoal updatedGoal = goal.get();
            updatedGoal.setName(goalDetails.getName());
            updatedGoal.setTargetAmount(goalDetails.getTargetAmount());
            updatedGoal.setSavedAmount(goalDetails.getSavedAmount());
            savingsGoalService.createGoal(updatedGoal.getUser(), updatedGoal.getName(), updatedGoal.getTargetAmount());
            return ResponseEntity.ok(updatedGoal);
        }
        return ResponseEntity.badRequest().body(Map.of("message", "Goal not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGoal(@PathVariable Long id) {
        boolean deleted = savingsGoalService.deleteGoal(id);
        if (deleted) {
            return ResponseEntity.ok(Map.of("message", "Goal deleted successfully"));
        }
        return ResponseEntity.badRequest().body(Map.of("message", "Goal not found"));
    }
}
