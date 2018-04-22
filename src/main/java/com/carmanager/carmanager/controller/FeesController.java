package com.carmanager.carmanager.controller;


import com.carmanager.carmanager.model.Fees;
import com.carmanager.carmanager.service.FeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/fees/")
public class FeesController {

    @Autowired
    private FeesService feesService;


    @RequestMapping(path = "/listfees", method = RequestMethod.GET)
    public List<Fees> listExpenses() {
        List<Fees> feesList = feesService.getAllFees().stream()
                .map(fees -> new Fees(fees.getName()))
                .collect(Collectors.toList());

        return feesList;
    }

}