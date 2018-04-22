package com.carmanager.carmanager.service;

import com.carmanager.carmanager.model.Repairs;
import com.carmanager.carmanager.repository.RepairsRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Optional;

public class RepairService implements IRepairService {


    @Autowired
    private RepairsRepository repairsRepository;


    @Override
    public void addNewRepair(Repairs repairs) {
        repairs.setRepairDate(LocalDate.now());
        repairsRepository.save(repairs);
    }

    @Override
    public void removeRepair(Repairs repairs) {
        repairsRepository.delete(repairs);
    }

    @Override
    public void editRepair(Repairs repairs) {

    }

    @Override
    public Optional<Repairs> findRepairbyId(Long repairId) {
        return repairsRepository.findById(repairId);
    }
}
