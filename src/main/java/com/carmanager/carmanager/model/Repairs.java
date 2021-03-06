package com.carmanager.carmanager.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private String repairCost;
    private LocalDate repairDate;
    private String repairDescription;

    @ManyToOne
    @JsonBackReference
    private Car car;

    public Repairs(Long id, String name, String workshop, String repairCost, LocalDate repairDate, String repairDescription) {
        this.id = id;
        this.name = name;
        this.workshop = workshop;
        this.repairCost = repairCost;
        this.repairDate = repairDate;
        this.repairDescription = repairDescription;
    }

    public Repairs(String name, String workshop, String repairCost, LocalDate repairDate, String repairDescription) {
        this.name = name;
        this.workshop = workshop;
        this.repairCost = repairCost;
        this.repairDate = repairDate;
        this.repairDescription = repairDescription;
    }
}