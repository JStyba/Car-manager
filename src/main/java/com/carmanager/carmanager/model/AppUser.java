package com.carmanager.carmanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String login;
    private String email;
    private String password;

    public AppUser(String login, String password, Role role) {
        this.login = login;
        this.password = password;
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        this.roleSet = roles;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roleSet;

}