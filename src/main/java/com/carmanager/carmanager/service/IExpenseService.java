package com.carmanager.carmanager.service;

import com.carmanager.carmanager.exceptions.ElementNotFound;
import com.carmanager.carmanager.model.Car;
import com.carmanager.carmanager.model.Expenses;
import com.carmanager.carmanager.model.dto.AddExpenseDto;

import java.util.List;
import java.util.Optional;

public interface IExpenseService {

    void addNewExpense (Expenses expenses);

    void addNewExpense(AddExpenseDto expense);

    void editCar (Expenses expenses);
    void addNewEntryInExpense (String expense);
    Optional<Expenses> findExpenseById (Long id);
    void removeExpense (Long id) throws ElementNotFound;

    List<Expenses> getAll();

}
