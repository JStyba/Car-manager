package com.carmanager.carmanager.component;


import com.carmanager.carmanager.model.Car;
import com.carmanager.carmanager.model.Expenses;
import com.carmanager.carmanager.model.Fees;
import com.carmanager.carmanager.repository.ExpensesRepository;
import com.carmanager.carmanager.repository.FeesRepository;
import com.carmanager.carmanager.repository.RepairsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer {

    private ExpensesRepository expensesRepository;
    private FeesRepository feesRepository;
    private RepairsRepository repairsRepository;

    @Autowired
    public DataInitializer(ExpensesRepository expensesRepository, FeesRepository feesRepository, RepairsRepository repairsRepository) {
        this.expensesRepository = expensesRepository;
        this.feesRepository = feesRepository;
        this.repairsRepository = repairsRepository;

        loadFees();
        loadExpenses();
//        loadRepairs();
    }

//    private void loadRepairs() {
//
//    }

    private void loadExpenses() {
        expensesRepository.save(new Expenses("Vulcanization", LocalDate.now(),"200","usługa"));

    }

    private void loadFees() {
            feesRepository.save(new Fees("Insurance OC"));
            feesRepository.save(new Fees("Insurance AC"));
    }
}
