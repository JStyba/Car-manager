package com.carmanager.carmanager.service;

import com.carmanager.carmanager.exceptions.RegistrationException;
import com.carmanager.carmanager.model.AppUser;

public interface IAppUserService {

        void register(AppUser appUser) throws RegistrationException;
}
