package com.carmanager.carmanager.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


@Entity


public class Expenses{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonBackReference
    private Car car;

    private String name;
    private LocalDate expenseDate;
    private String expenseCost;
    private String expenseDescription;

    public Expenses() {
    }

    public Expenses(String name) {
        this.name = name;
    }

    public Expenses(Long id, String name, LocalDate expenseDate, String expenseCost, String expenseDescription) {
        this.id = id;
        this.name = name;
        this.expenseDate = expenseDate;
        this.expenseCost = expenseCost;
        this.expenseDescription = expenseDescription;
    }

    public Expenses(String name, LocalDate expenseDate, String expenseCost, String expenseDescription) {
        this.name = name;
        this.expenseDate = expenseDate;
        this.expenseCost = expenseCost;
        this.expenseDescription = expenseDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDate expenseDate) {
        this.expenseDate = expenseDate;
    }

    public String getExpenseCost() {
        return expenseCost;
    }

    public void setExpenseCost(String expenseCost) {
        this.expenseCost = expenseCost;
    }

    public String getExpenseDescription() {
        return expenseDescription;
    }

    public void setExpenseDescription(String expenseDescription) {
        this.expenseDescription = expenseDescription;
    }
}
