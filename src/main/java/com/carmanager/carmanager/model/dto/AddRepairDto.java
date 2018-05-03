package com.carmanager.carmanager.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddRepairDto {
    private Long carOwnerId;
    private Long carId;
    private String name;
    private String workshop;
    @JsonIgnore
    private LocalDate repairDate = LocalDate.now();
    private String repairCost;
    private String repairDescription;
}
