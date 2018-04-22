package com.carmanager.carmanager.service;

import com.carmanager.carmanager.exceptions.ElementNotFound;
import com.carmanager.carmanager.model.AppUser;
import com.carmanager.carmanager.model.Car;
import com.carmanager.carmanager.model.Expenses;
import com.carmanager.carmanager.model.dto.PageResponse;
import com.carmanager.carmanager.repository.ExpensesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public void editCar(Expenses expenses) {
    }

    @Override
    public void addNewEntryInExpense(String expense) {
        expensesRepository.save(new Expenses(expense));
    }



    @Override
    public Optional<Expenses> findExpenseById(Long id) {
        return expensesRepository.findById(id);
    }

    @Override
    public void removeExpense(Long id) throws ElementNotFound {
        if (id==null){
            throw new ElementNotFound();
        }
        expensesRepository.deleteById(id);
    }


    @Override
    public List<Expenses> getAll() {
        return expensesRepository.findAll();
    }
}
