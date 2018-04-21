package com.carmanager.carmanager.repository;

import com.carmanager.carmanager.model.AppUser;
import com.carmanager.carmanager.model.Repairs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepairsRepository extends JpaRepository<Repairs, Long> {

}
