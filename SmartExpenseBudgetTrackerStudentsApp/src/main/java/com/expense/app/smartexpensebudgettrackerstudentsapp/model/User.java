package com.expense.app.smartexpensebudgettrackerstudentsapp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    private String otp;
    private Boolean verified = false;
    private String currency;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Expense> expenses;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Budget budget;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<SavingsGoal> savingsGoals;

    public User(Long id,String email, String name, String otp, Boolean verified) {
        this.email = email;
        this.name = name;
        this.otp = otp;
        this.verified = verified;
    }
}
