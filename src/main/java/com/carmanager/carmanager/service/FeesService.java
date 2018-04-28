package com.carmanager.carmanager.service;

import com.carmanager.carmanager.exceptions.ElementNotFound;
import com.carmanager.carmanager.model.Fees;
import com.carmanager.carmanager.repository.FeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeesService implements IFeesService {

    @Autowired
    private FeesRepository feesRepository;


    @Override
    public void removeFee(Long id) throws ElementNotFound {
        if(id==null){
            throw new ElementNotFound();
        }
        feesRepository.deleteById(id);
    }

    @Override
    public void editFee(Long id, Fees fees) {
        feesRepository.save(fees);
    }

    @Override
    public List<Fees> getAllFees() {
        List<Fees> fees = new ArrayList<>();
        feesRepository.findAll()
                .forEach(fees :: add);
        return fees;
    }

    @Override
    public void addNewFee(Fees fees) {
        feesRepository.save(fees);
    }

    @Override
    public void addNewEntryInFees(String fees) {
        feesRepository.save(new Fees(fees));
    }
}
