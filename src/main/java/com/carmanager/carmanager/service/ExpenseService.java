package com.carmanager.carmanager.service;

import com.carmanager.carmanager.model.Car;
import com.carmanager.carmanager.model.Expenses;
import com.carmanager.carmanager.repository.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
public class ExpenseService implements IExpenseService {


    @Autowired
    private ExpensesRepository expensesRepository;

    @Override
    public void addNewExpense(Expenses expenses) {
        expenses.setExpenseDate(LocalDate.now());
        expensesRepository.save(expenses);
    }

    @Override
    public void removeExpense(Expenses expenses) {
        expensesRepository.delete(expenses);
    }

    @Override
    public void editCar(Expenses expenses) {

    }

    @Override
    public void addNewEntryInExpense(String expense) {
        expensesRepository.save(new Expenses(expense));
    }

    @Override
    public List<Expenses> getAll() {
        return expensesRepository.findAll();
    }
}
