package com.carmanager.carmanager.controller;

import com.carmanager.carmanager.exceptions.UserDoesNotExistException;
import com.carmanager.carmanager.model.AppUser;
import com.carmanager.carmanager.model.Role;
import com.carmanager.carmanager.model.dto.AuthenticationDto;
import com.carmanager.carmanager.model.dto.LoginDto;
import com.carmanager.carmanager.model.dto.RespFactory;
import com.carmanager.carmanager.service.IAppUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

            SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

            //We will sign our JWT with our ApiKey secret
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET);
            Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

            String token = Jwts.builder()
                    .setSubject(user.getLogin())
                    .setIssuedAt(new Date())
                    .claim(AUTHORITIES_KEY, translateRoles(user.getRoleSet())) // todo: do zmiany na getRoles?
                    .signWith(signatureAlgorithm, signingKey)
                    .compact();

            return RespFactory.result(new AuthenticationDto(token, user));
        } catch (UserDoesNotExistException e) {
            e.printStackTrace();
        }
        return RespFactory.badRequest();
    }

    private Set<String> translateRoles(Set<Role> roles) {
        return roles.stream().map(role -> role.getName()).collect(Collectors.toSet());
    }
}