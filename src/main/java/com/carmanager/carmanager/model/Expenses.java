package com.carmanager.carmanager.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor

public class Expenses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Car car;

    private String name;
    private LocalDate expenseDate;
    private String expenseCost;
    private String expenseDescription;
    @Enumerated (EnumType.STRING)
    private ExpensesEnum expensesEnum;

    public Expenses(String name) {
        this.name = name;
    }

    public Expenses(String name, LocalDate expenseDate, String expenseCost, String expenseDescription) {

        this.name = name;
        this.expenseDate = expenseDate;
        this.expenseCost = expenseCost;
        this.expenseDescription = expenseDescription;

    }
}
