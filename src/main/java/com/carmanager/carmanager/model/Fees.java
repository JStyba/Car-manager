package com.carmanager.carmanager.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class Fees {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonBackReference
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

    public Fees(String name, LocalDate feeDate, String feeCost, LocalDate feeExpirationDate, String feeDescription) {
        this.name = name;
        this.feeDate = feeDate;
        this.feeCost = feeCost;
        this.feeExpirationDate = feeExpirationDate;
        this.feeDescription = feeDescription;
    }
}
