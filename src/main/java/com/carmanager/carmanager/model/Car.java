package com.carmanager.carmanager.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Car {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fuelType;
    private String make;
    private String model;
    private int dateProduced;
    private String vin;
    private Double engineCapacity;
    private int numberOfSeats;
    private String registrationNumber;


    @OneToMany
    private List<Repairs> repairsList;


    @ManyToOne(cascade = CascadeType.PERSIST)
    private AppUser appUser;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "car")
    private Set<Expenses> expensesSet;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "car")
    private Set<Fees> feesSet;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "car")
    private Set<Repairs> repairsSet;


}
