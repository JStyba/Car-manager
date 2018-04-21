package com.carmanager.carmanager.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Fees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Car car;

    private String name;
    private LocalDate feeDate;
    private String feeCost;
    private LocalDate feeExpirationDate;
    private String feeDescription;

    public Fees(String name) {
        this.name = name;
    }
}
