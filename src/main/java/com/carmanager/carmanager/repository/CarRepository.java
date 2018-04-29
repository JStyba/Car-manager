package com.carmanager.carmanager.repository;

import com.carmanager.carmanager.model.AppUser;
import com.carmanager.carmanager.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByAppUser(AppUser user);
}
