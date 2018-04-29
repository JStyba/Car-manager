package com.carmanager.carmanager.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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


    public Car(long id, String fuelType, String make, String model, int dateProduced, String vin, double engineCapacity, int numberOfSeats, String registrationNumber) {
        this.id = id;
        this.fuelType = fuelType;
        this.make = make;
        this.model = model;
        this.dateProduced = dateProduced;
        this.vin = vin;
        this.engineCapacity = engineCapacity;
        this.numberOfSeats = numberOfSeats;
        this.registrationNumber = registrationNumber;


    }
}
