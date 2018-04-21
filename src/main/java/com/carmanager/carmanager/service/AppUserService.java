package com.carmanager.carmanager.service;

import com.carmanager.carmanager.exceptions.RegistrationException;
import com.carmanager.carmanager.exceptions.UserLoginAlreadyExistsException;
import com.carmanager.carmanager.model.AppUser;
import com.carmanager.carmanager.repository.AppUserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService implements IAppUserService{

    private AppUserRepository appUserRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void register(AppUser appUser) throws RegistrationException {
        appUser.setLogin(appUser.getLogin().toLowerCase());

        Optional<AppUser> loginUser = appUserRepository.findByLogin(appUser.getLogin());
        if (loginUser.isPresent()) {
            throw new UserLoginAlreadyExistsException();
        }

        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));

        appUserRepository.save(appUser);
    }
}
