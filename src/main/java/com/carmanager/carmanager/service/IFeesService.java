package com.carmanager.carmanager.service;

import com.carmanager.carmanager.exceptions.ElementNotFound;
import com.carmanager.carmanager.model.Fees;
import com.carmanager.carmanager.model.dto.AddFeeDto;

import java.util.List;

public interface IFeesService {
    void removeFee(Long id) throws ElementNotFound;
    void editFee(Long id, Fees fees);
    List<Fees> getAllFees();
    void addNewFee(Fees fee);
    void addNewFee(AddFeeDto fee);
    void addNewEntryInFees(String fees);
}
