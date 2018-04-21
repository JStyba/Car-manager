package com.carmanager.carmanager.repository;

import com.carmanager.carmanager.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RepairsRepository extends JpaRepository<AppUser, Long> {

}