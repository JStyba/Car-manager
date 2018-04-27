package com.carmanager.carmanager.service;

import com.carmanager.carmanager.exceptions.RegistrationException;
import com.carmanager.carmanager.model.AppUser;
import com.carmanager.carmanager.model.dto.PageResponse;

import java.util.Optional;

public interface IAppUserService {
        void register(AppUser appUser) throws RegistrationException;

        PageResponse<AppUser> getUsers(int page);

        PageResponse<AppUser> getAllUsers();

        Optional<AppUser> getUserWithId(long ownerId);

}