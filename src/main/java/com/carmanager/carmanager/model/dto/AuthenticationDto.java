package com.carmanager.carmanager.model.dto;

import com.carmanager.carmanager.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationDto {
    private String token;
    private AppUser user;
}
