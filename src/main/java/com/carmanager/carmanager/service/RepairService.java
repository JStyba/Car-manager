package com.carmanager.carmanager.service;

import com.carmanager.carmanager.exceptions.ElementNotFound;
import com.carmanager.carmanager.model.Repairs;
import com.carmanager.carmanager.repository.RepairsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RepairService implements IRepairService {

    @Autowired
    private RepairsRepository repairsRepository;

    @Override
    public void addNewRepair(Repairs repairs) {
        repairs.setRepairDate(LocalDate.now());
        repairsRepository.save(repairs);
    }

    @Override
    public void removeRepair(Long id) throws ElementNotFound {
        if (id==null){
            throw new ElementNotFound();
        }
        repairsRepository.deleteById(id);
    }

    @Override
    public void editRepair(Long id) {
        repairsRepository.findById(id);
    }

    @Override
    public List<Repairs> getAllRepairs() {
        return repairsRepository.findAll();
    }
}
