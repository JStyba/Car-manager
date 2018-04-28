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

    public Fees(Long id, String name, LocalDate feeDate, String feeCost, LocalDate feeExpirationDate, String feeDescription) {
        this.id = id;
        this.name = name;
        this.feeDate = feeDate;
        this.feeCost = feeCost;
        this.feeExpirationDate = feeExpirationDate;
        this.feeDescription = feeDescription;
    }
}
