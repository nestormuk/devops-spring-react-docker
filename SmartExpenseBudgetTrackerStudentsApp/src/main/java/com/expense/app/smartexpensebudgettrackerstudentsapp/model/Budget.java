package com.expense.app.smartexpensebudgettrackerstudentsapp.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;
    private String period; // "monthly" or "weekly"

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
