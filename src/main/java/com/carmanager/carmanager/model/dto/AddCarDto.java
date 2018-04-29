package com.carmanager.carmanager.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCarDto {
    private Long ownerId;
    private String fuelType;
    private String make;
    private String model;
    private int dateProduced;
    private String vin;
    private Double engineCapacity;
    private int numberOfSeats;
    private String registrationNumber;
}
