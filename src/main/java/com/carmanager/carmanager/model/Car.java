package com.carmanager.carmanager.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;


@Entity

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


    @ManyToOne
    @JsonIgnore
    private AppUser appUser;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "car")
    @JsonManagedReference
    private Set<Expenses> expensesSet;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "car")
    private Set<Fees> feesSet;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "car")
    private Set<Repairs> repairsSet;

    public Car() {
    }

    public Car(String fuelType, String make, String model, int dateProduced, String vin, Double engineCapacity, int numberOfSeats, String registrationNumber) {
        this.fuelType = fuelType;
        this.make = make;
        this.model = model;
        this.dateProduced = dateProduced;
        this.vin = vin;
        this.engineCapacity = engineCapacity;
        this.numberOfSeats = numberOfSeats;
        this.registrationNumber = registrationNumber;
    }

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getDateProduced() {
        return dateProduced;
    }

    public void setDateProduced(int dateProduced) {
        this.dateProduced = dateProduced;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(Double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public List<Repairs> getRepairsList() {
        return repairsList;
    }

    public void setRepairsList(List<Repairs> repairsList) {
        this.repairsList = repairsList;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Set<Expenses> getExpensesSet() {
        return expensesSet;
    }

    public void setExpensesSet(Set<Expenses> expensesSet) {
        this.expensesSet = expensesSet;
    }

    public Set<Fees> getFeesSet() {
        return feesSet;
    }

    public void setFeesSet(Set<Fees> feesSet) {
        this.feesSet = feesSet;
    }

    public Set<Repairs> getRepairsSet() {
        return repairsSet;
    }

    public void setRepairsSet(Set<Repairs> repairsSet) {
        this.repairsSet = repairsSet;
    }
}
