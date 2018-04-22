package com.carmanager.carmanager.controller;


import com.carmanager.carmanager.exceptions.RegistrationException;
import com.carmanager.carmanager.model.AppUser;
import com.carmanager.carmanager.model.dto.PageResponse;
import com.carmanager.carmanager.model.dto.RespFactory;
import com.carmanager.carmanager.model.dto.Response;
import com.carmanager.carmanager.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user/")
public class AppUserController {

    private AppUserService appUserService;

    @Autowired
    public AppUserController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public ResponseEntity<Response> register(@RequestBody AppUser appUser) {
        try {
            appUserService.register(appUser);
        } catch (RegistrationException e) {
            return RespFactory.badRequest();
        }

        return RespFactory.created();
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ResponseEntity<Response> list() {
        PageResponse<AppUser> list = appUserService.getAllUsers();

        return RespFactory.result(list);
    }
}