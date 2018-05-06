package com.carmanager.carmanager.controller;

import com.carmanager.carmanager.exceptions.UserDoesNotExistException;
import com.carmanager.carmanager.model.AppUser;
import com.carmanager.carmanager.model.dto.AuthenticationDto;
import com.carmanager.carmanager.model.dto.LoginDto;
import com.carmanager.carmanager.model.dto.RespFactory;
import com.carmanager.carmanager.service.IAppUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

import static com.carmanager.carmanager.configuration.JWTFilter.AUTHORITIES_KEY;
import static com.carmanager.carmanager.configuration.JWTFilter.SECRET;

@RestController
@CrossOrigin
public class AuthorizationController {

    @Autowired
    private IAppUserService appUserService;

    @RequestMapping(path = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationDto> authenticate(@RequestBody LoginDto dto) {
        try {
            Optional<AppUser> appUserOptional = appUserService.getUserWithLoginAndPassword(dto);

            AppUser user = appUserOptional.get();

            String token = Jwts.builder()
                    .setSubject(user.getUsername())
                    .setIssuedAt(new Date())
                    .claim(AUTHORITIES_KEY, user.getAuthorities())
                    .signWith(SignatureAlgorithm.HS256, SECRET)
                    .compact();

            return RespFactory.result(new AuthenticationDto(token, user));
        } catch (UserDoesNotExistException e) {
            e.printStackTrace();
        }
        return RespFactory.badRequest();
    }
}
