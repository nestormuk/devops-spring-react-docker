package com.expense.app.smartexpensebudgettrackerstudentsapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavingsGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double targetAmount;
    private Double savedAmount = 0.0;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}