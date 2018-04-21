package com.carmanager.carmanager.repository;

import com.carmanager.carmanager.model.AppUser;
import com.carmanager.carmanager.model.Fees;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeesRepository extends JpaRepository<Fees, Long> {

}
