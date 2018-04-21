package com.carmanager.carmanager.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String make;
    private String model;
    private int dateProduced;
    private String vin;
    private Double engineCapacity;
    private int numberOfSeats;
    private String registrationNumber;


    @OneToMany (mappedBy = "repair")
    private List<Repairs> repairsList;
    @ManyToOne
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "appuser")
    private AppUser appUser;





}
