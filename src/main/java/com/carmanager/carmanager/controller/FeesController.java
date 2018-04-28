package com.carmanager.carmanager.controller;


import com.carmanager.carmanager.exceptions.ElementNotFound;
import com.carmanager.carmanager.model.Fees;
import com.carmanager.carmanager.service.FeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/fees/")
public class FeesController {

    @Autowired
    private FeesService feesService;


    @RequestMapping(path = "/listfees", method = RequestMethod.GET)
    public List<Fees> listFees() {
        List<Fees> feesList = feesService.getAllFees().stream()
                .map(fees -> new Fees(fees.getName()))
                .collect(Collectors.toList());

        return feesList;
    }
    @RequestMapping(path = "/addfees", method = RequestMethod.POST)
    public void addFee(@RequestBody Fees fees) {
        feesService.addNewFee(fees);
    }
    @RequestMapping(path = "/editfee/{id}", method = RequestMethod.PUT)
    public void editFee(@PathVariable Long id,@RequestBody Fees fees) {
       feesService.editFee(id, fees);
    }
    @RequestMapping(path = "/remove/{id}", method = RequestMethod.DELETE)
    public void removeFee(@PathVariable Long id) {
        try {
            feesService.removeFee(id);
        } catch (ElementNotFound elementNotFound) {
            elementNotFound.printStackTrace();
        }
    }

}