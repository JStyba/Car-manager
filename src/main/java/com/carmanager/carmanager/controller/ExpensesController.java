package com.carmanager.carmanager.controller;

import com.carmanager.carmanager.exceptions.ElementNotFound;
import com.carmanager.carmanager.exceptions.RegistrationException;
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
@RequestMapping("/expenses/")
public class ExpensesController {

    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private ExpensesRepository expensesRepository;


    @RequestMapping(path = "/listexpenses", method = RequestMethod.GET)
    public List<Expenses> listExpenses() {
        List<Expenses> expensesList = expenseService.getAll().stream()
                .map(expenses -> new Expenses(expenses.getId(),
                        expenses.getName()
                        , expenses.getExpenseDate()
                        , expenses.getExpenseCost()
                        , expenses.getExpenseDescription()))
                .collect(Collectors.toList());
        return expensesList;
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public ResponseEntity<Expenses> getExpense(@RequestParam(name = "id") Long id){
        Optional<Expenses> expenses = expensesRepository.findById(id);
        if(expenses.isPresent()){
            return RespFactory.result(expenses.get());
        }
        return RespFactory.badRequest();
    }

    @RequestMapping(path = "/edit-expense", method = RequestMethod.POST)
    public ResponseEntity<Response> editExpense(@RequestBody Expenses expense) throws ElementNotFound {

//        expense.setName(expense.getName());
//        expense.setExpenseCost(expense.getExpenseCost());
//        expense.setExpenseDescription(expense.getExpenseDescription());

        Optional<Expenses> expenseId = expensesRepository.findById(expense.getId());
        if (!expenseId.isPresent()) {
            throw new ElementNotFound();
        }
        expensesRepository.saveAndFlush(expense);
        return RespFactory.ok("Expense edited");
    }
    @RequestMapping(path = "/remove-expense/{expenseId}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> removeExpense(@PathVariable("expenseId") Long id) {
        try {
            expenseService.removeExpense(id);
        } catch (ElementNotFound e) {
            return RespFactory.badRequest();
        }
        return RespFactory.ok("expense deleted");
    }

    @RequestMapping(path = "/add-expense", method = RequestMethod.POST)
    public ResponseEntity<Response> addExpense(@RequestBody Expenses expenses) {

        expenseService.addNewExpense(expenses);
        return RespFactory.created();
    }
}
