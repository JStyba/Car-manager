package com.carmanager.carmanager.service;

import com.carmanager.carmanager.exceptions.RegistrationException;
import com.carmanager.carmanager.exceptions.UserEmailAlreadyExistsException;
import com.carmanager.carmanager.exceptions.UserLoginAlreadyExistsException;
import com.carmanager.carmanager.model.AppUser;
import com.carmanager.carmanager.model.dto.PageResponse;
import com.carmanager.carmanager.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService implements IAppUserService {

    private static final int DEFAULT_PAGE_SIZE = 10;
    private AppUserRepository appUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public AppUserService(AppUserRepository appUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void register(AppUser appUser) throws RegistrationException {
        appUser.setEmail(appUser.getEmail().toLowerCase());
        appUser.setLogin(appUser.getLogin().toLowerCase());

        Optional<AppUser> emailUser = appUserRepository.findByEmail(appUser.getEmail());
        if (emailUser.isPresent()) {
            throw new UserEmailAlreadyExistsException();
        }
        Optional<AppUser> loginUser = appUserRepository.findByLogin(appUser.getLogin());
        if (loginUser.isPresent()) {
            throw new UserLoginAlreadyExistsException();
        }

        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));

        appUserRepository.save(appUser);
    }

    public PageResponse<AppUser> getAllUsers() {
        return getUsers(0);
    }

    @Override
    public Optional<AppUser> getUserWithId(long ownerId) {
        return appUserRepository.findById(ownerId);
    }

    public PageResponse<AppUser> getUsers(int page) {
        Page<AppUser> users = appUserRepository.findAllBy(PageRequest.of(page, DEFAULT_PAGE_SIZE));

        return new PageResponse<>(users);
    }
}