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
        loadUsers();
//        loadRepairs();
    }
    private void loadData() {
        Role adminRole = new Role("ADMIN");
        adminRole = roleRepository.saveAndFlush(adminRole);
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("GUEST"));
    }
    private void loadExpenses() {
        expensesRepository.save(new Expenses(1l,"Vulcanization", LocalDate.now(),"200","usługa"));
        expensesRepository.save(new Expenses(2l,"Repair", LocalDate.now(),"100","deletethis"));

    }
    private void loadFees() {
            feesRepository.save(new Fees(1l,"Insurance AC", LocalDate.now(), "100", LocalDate.now().plusYears(1), "next fee"));
            feesRepository.save(new Fees(2l,"Insurance OC", LocalDate.now(), "200", LocalDate.now().plusYears(1), "next fee"));
    }

    private void loadUsers () {
        appUserRepository.save(new AppUser("admin@admin","admin","haslo"));
    }
}


