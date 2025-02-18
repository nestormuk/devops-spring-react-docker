package com.expense.app.smartexpensebudgettrackerstudentsapp.controller;

import com.expense.app.smartexpensebudgettrackerstudentsapp.model.Expense;
import com.expense.app.smartexpensebudgettrackerstudentsapp.model.User;
import com.expense.app.smartexpensebudgettrackerstudentsapp.repository.UserRepository;
import com.expense.app.smartexpensebudgettrackerstudentsapp.service.ExpenseServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseServiceImp expenseService;
    private final UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<?> addExpense(@RequestBody Map<String, Object> request) {
        Optional<User> user = userRepository.findByEmail((String) request.get("email"));
        if (user.isPresent()) {
            Expense expense = new Expense(null, (String) request.get("category"),
                    (Double) request.get("amount"),
                    (String) request.get("description"),
                    null, user.get());
            return ResponseEntity.ok(expenseService.addExpense(user.get(), expense));
        }
        return ResponseEntity.badRequest().body(Map.of("message", "User not found"));
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<Expense>> getExpenses(@PathVariable String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.map(value -> ResponseEntity.ok(expenseService.getUserExpenses(value)))
                .orElseGet(() -> ResponseEntity.badRequest().body(null));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateExpense(@PathVariable Long id, @RequestBody Expense expenseDetails) {
        Optional<Expense> expense = expenseService.getExpenseById(id);
        if (expense.isPresent()) {
            Expense updatedExpense = expense.get();
            updatedExpense.setCategory(expenseDetails.getCategory());
            updatedExpense.setAmount(expenseDetails.getAmount());
            updatedExpense.setDescription(expenseDetails.getDescription());
            updatedExpense.setDate(expenseDetails.getDate());
            expenseService.addExpense(updatedExpense.getUser(), updatedExpense); // Save updated expense
            return ResponseEntity.ok(updatedExpense);
        }
        return ResponseEntity.badRequest().body(Map.of("message", "Expense not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
        boolean deleted = expenseService.deleteExpense(id);
        if (deleted) {
            return ResponseEntity.ok(Map.of("message", "Expense deleted successfully"));
        }
        return ResponseEntity.badRequest().body(Map.of("message", "Expense not found"));
    }
}