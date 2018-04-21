package com.carmanager.carmanager.repository;

import com.carmanager.carmanager.model.AppUser;
import com.carmanager.carmanager.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpensesRepository extends JpaRepository<Expenses, Long> {

}
