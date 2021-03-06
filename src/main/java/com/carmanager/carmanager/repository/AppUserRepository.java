package com.carmanager.carmanager.repository;

import com.carmanager.carmanager.model.AppUser;
import com.carmanager.carmanager.model.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByLogin(String login);

    Optional<AppUser> findByEmail(String email);

    Page<AppUser> findAllBy(Pageable pageable);

    List <Car> getAllById (Long id);

    Optional<AppUser> findByLoginAndPassword(String username, String password);
}