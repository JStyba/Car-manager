package com.carmanager.carmanager.service;

import com.carmanager.carmanager.model.Car;
import com.carmanager.carmanager.model.Expenses;

import java.util.List;
import java.util.Optional;

public interface IExpenseService {

    void addNewExpense (Expenses expenses);
    void removeExpense (Expenses expenses);
    void editCar (Expenses expenses);
    void addNewEntryInExpense (String expense);

    List<Expenses> getAll();

}
