package com.carmanager.carmanager.controller;

import com.carmanager.carmanager.model.Expenses;
import com.carmanager.carmanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping ("/expenses/")
public class ExpensesController {

    @Autowired
    private ExpenseService expenseService;


    @RequestMapping(path = "/listexpenses", method = RequestMethod.GET)
    public List<Expenses> listExpenses() {
        List<Expenses> expensesList = expenseService.getAll().stream()
                .map(expenses -> new Expenses(expenses.getName(), expenses.getExpenseDate(),expenses.getExpenseCost(),expenses.getExpenseDescription()))
                .collect(Collectors.toList());
        return expensesList;
    }
}
