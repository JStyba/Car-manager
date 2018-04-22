package com.carmanager.carmanager.service;

import com.carmanager.carmanager.model.Car;
import com.carmanager.carmanager.model.Fees;

import java.util.List;

public interface IFeesService {
    void removeFee(Fees fees);
    void editFee(Fees fees);
    List<Fees> getAllFees();
    void addNewFee(String fee);
    void addNewEntryInFees(String fees);
}
