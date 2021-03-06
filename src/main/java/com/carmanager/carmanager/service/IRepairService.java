package com.carmanager.carmanager.service;

import com.carmanager.carmanager.exceptions.ElementNotFound;
import com.carmanager.carmanager.model.Car;
import com.carmanager.carmanager.model.Repairs;
import com.carmanager.carmanager.model.dto.AddFeeDto;
import com.carmanager.carmanager.model.dto.AddRepairDto;

import java.util.List;
import java.util.Optional;

public interface IRepairService {

    void addNewRepair(Repairs repairs);
    void removeRepair(Long car) throws ElementNotFound;
    void editRepair(Long id);
    List<Repairs> getAllRepairs();
    void addNewRepair(AddRepairDto repair);
}
