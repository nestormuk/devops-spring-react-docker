package com.expense.app.smartexpensebudgettrackerstudentsapp.service;

import com.expense.app.smartexpensebudgettrackerstudentsapp.model.User;

public interface UserService {
    User registerUser(String name, String email);
    boolean verifyOtp(String email, String otp);
}