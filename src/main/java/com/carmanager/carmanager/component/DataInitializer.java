package com.carmanager.carmanager.component;


import com.carmanager.carmanager.model.*;
import com.carmanager.carmanager.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataInitializer {

    private ExpensesRepository expensesRepository;
    private FeesRepository feesRepository;
    private RepairsRepository repairsRepository;
    private RoleRepository roleRepository;
    private AppUserRepository appUserRepository;
    @Autowired
    public DataInitializer(ExpensesRepository expensesRepository, FeesRepository feesRepository, RepairsRepository repairsRepository, RoleRepository roleRepository, AppUserRepository appUserRepository) {
        this.expensesRepository = expensesRepository;
        this.feesRepository = feesRepository;
        this.repairsRepository = repairsRepository;
        this.roleRepository = roleRepository;
        this.appUserRepository = appUserRepository;

        loadFees();
        loadExpenses();
//        loadRepairs();
    }

//    private void loadRepairs() {
//
//    }

    private void loadData() {
        Role adminRole = new Role("ADMIN");
        adminRole = roleRepository.saveAndFlush(adminRole);
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("GUEST"));

        appUserRepository.save(new AppUser("admin", "admin", adminRole));
    }

    private void loadExpenses() {
        expensesRepository.save(new Expenses("Vulcanization", LocalDate.now(),"200","usługa"));

    }

    private void loadFees() {
            feesRepository.save(new Fees("Insurance OC"));
            feesRepository.save(new Fees("Insurance AC"));
    }
    
}

