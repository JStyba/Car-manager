package com.carmanager.carmanager.controller;


import com.carmanager.carmanager.exceptions.ElementNotFound;
import com.carmanager.carmanager.model.Fees;
import com.carmanager.carmanager.model.dto.RespFactory;
import com.carmanager.carmanager.model.dto.Response;
import com.carmanager.carmanager.service.FeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
                .map(fees -> new Fees(fees.getId(),
                        fees.getName()
                        ,fees.getFeeDate()
                        ,fees.getFeeCost()
                        ,fees.getFeeExpirationDate()
                        ,fees.getFeeDescription()))
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

    @RequestMapping(path = "/remove-fee/{feeId}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> removeExpense(@PathVariable ("feeId") Long id) {
        try {
            feesService.removeFee(id);
        }catch (ElementNotFound e){
            return RespFactory.badRequest();
        }
        return RespFactory.ok("fee deleted");
    }

}