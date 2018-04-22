package com.carmanager.carmanager.model;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

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
    private double cost;
    private LocalDate repairDate;
    private String description;

    @ManyToOne
    private Car car;
}
