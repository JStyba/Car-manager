package com.carmanager.carmanager.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddExpenseDto {
    private Long carOwnerId;
    private Long carId;
    private String name;
    @JsonIgnore
    private LocalDate expenseDate = LocalDate.now();
    private String expenseCost;
    private String expenseDescription;

}
