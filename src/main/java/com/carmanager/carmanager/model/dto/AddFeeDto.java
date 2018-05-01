package com.carmanager.carmanager.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddFeeDto {
    private Long carOwnerId;
    private Long carId;
    private String name;
    @JsonIgnore
    private LocalDate feeDate = LocalDate.now();
    private String feeCost;
    @JsonIgnore
    private LocalDate feeExpirationDate = LocalDate.now().plusYears(1);
    private String feeDescription;
}
