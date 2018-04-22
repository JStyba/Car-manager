package com.carmanager.carmanager.service;

import com.carmanager.carmanager.model.Fees;
import com.carmanager.carmanager.repository.FeesRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FeesService implements IFeesService {

    @Autowired
    private FeesRepository feesRepository;


    @Override
    public void removeFee(Fees fees) {
    feesRepository.delete(fees);
    }

    @Override
    public void editFee(Fees fees) {
        
    }

    @Override
    public List<Fees> getAllFees() {
        return feesRepository.findAll();
    }

    @Override
    public void addNewFee(String fees) {
        feesRepository.save(new Fees(fees));
    }

    @Override
    public void addNewEntryInFees(String fees) {
        feesRepository.save(new Fees(fees));
    }
}
