package com.carmanager.carmanager.service;

import com.carmanager.carmanager.model.Repairs;

import java.util.Optional;

public interface IRepairService {

    void addNewRepair (Repairs repairs);
    void removeRepair (Repairs repairs);
    void editRepair (Repairs repairs);

    Optional<Repairs> findRepairbyId (Long repairId);

}
