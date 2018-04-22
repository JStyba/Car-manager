package com.carmanager.carmanager.controller;

import com.carmanager.carmanager.exceptions.ElementNotFound;
import com.carmanager.carmanager.model.Expenses;
import com.carmanager.carmanager.model.dto.PageResponse;
import com.carmanager.carmanager.model.dto.RespFactory;
import com.carmanager.carmanager.model.dto.Response;
import com.carmanager.carmanager.repository.ExpensesRepository;
import com.carmanager.carmanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    @RequestMapping(path = "/remove-expense", method = RequestMethod.POST)
    public ResponseEntity<Response> removeExpense(@RequestParam("expenseid") Long id) {
        try {
            expenseService.removeExpense(id);
        }catch (ElementNotFound e){
            return RespFactory.badRequest();
        }
        return RespFactory.ok("expense deleted");
    }
}
