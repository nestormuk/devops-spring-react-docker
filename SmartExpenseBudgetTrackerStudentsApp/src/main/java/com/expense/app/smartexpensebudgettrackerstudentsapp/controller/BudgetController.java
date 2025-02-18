package com.expense.app.smartexpensebudgettrackerstudentsapp.controller;

import com.expense.app.smartexpensebudgettrackerstudentsapp.model.Budget;
import com.expense.app.smartexpensebudgettrackerstudentsapp.model.User;
import com.expense.app.smartexpensebudgettrackerstudentsapp.repository.UserRepository;
import com.expense.app.smartexpensebudgettrackerstudentsapp.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;
    private final UserRepository userRepository;

    @PostMapping("/set")
    public ResponseEntity<?> setBudget(@RequestBody Map<String, Object> request) {
        Optional<User> user = userRepository.findByEmail((String) request.get("email"));
        if (user.isPresent()) {
            Budget budget = budgetService.setBudget(user.get(), (Double) request.get("amount"), (String) request.get("period"));
            return ResponseEntity.ok(budget);
        }
        return ResponseEntity.badRequest().body("User not found");
    }
    @GetMapping("/get/{email}")
    public ResponseEntity<?> getUserBudget(@PathVariable String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get().getBudget());
        }
        return ResponseEntity.badRequest().body("User not found");
    }

    @PutMapping("/update/{email}")
    public ResponseEntity<?> updateBudget(@PathVariable String email, @RequestBody Map<String, Object> request) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            Budget updatedBudget = budgetService.setBudget(user.get(), (Double) request.get("amount"), (String) request.get("period"));
            return ResponseEntity.ok(updatedBudget);
        }
        return ResponseEntity.badRequest().body("User not found");
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<?> deleteBudget(@PathVariable String email) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent()) {
            user.get().setBudget(null);
            return ResponseEntity.ok(Map.of("message", "Budget deleted successfully"));
        }
        return ResponseEntity.badRequest().body("User not found");
    }
}
