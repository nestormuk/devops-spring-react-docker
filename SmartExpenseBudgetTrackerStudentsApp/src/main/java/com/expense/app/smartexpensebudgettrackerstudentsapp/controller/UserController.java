package com.expense.app.smartexpensebudgettrackerstudentsapp.controller;


import com.expense.app.smartexpensebudgettrackerstudentsapp.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, String> request) {
        com.expense.app.smartexpensebudgettrackerstudentsapp.model.User user = userService.registerUser(request.get("name"), request.get("email"));
        return ResponseEntity.ok(Map.of("message", "OTP sent to email"));
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> request) {
        boolean isVerified = userService.verifyOtp(request.get("email"), request.get("otp"));
        if (isVerified) {
            return ResponseEntity.ok(Map.of("message", "User verified successfully"));
        }
        return ResponseEntity.badRequest().body(Map.of("message", "Invalid OTP"));
    }
}