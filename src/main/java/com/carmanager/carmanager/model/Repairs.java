package com.carmanager.carmanager.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Repairs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String name;
    private String workshop;
    @NonNull
    private double repairCost;
    private LocalDate repairDate;
    private String repairDescription;

    @ManyToOne
    private Car car;

    public Repairs(Long id, String name, String workshop, double repairCost, LocalDate repairDate, String repairDescription) {
        this.id = id;
        this.name = name;
        this.workshop = workshop;
        this.repairCost = repairCost;
        this.repairDate = repairDate;
        this.repairDescription = repairDescription;
    }
}