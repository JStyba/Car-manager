package com.carmanager.carmanager.controller;


import com.carmanager.carmanager.exceptions.ElementNotFound;
import com.carmanager.carmanager.model.Fees;
import com.carmanager.carmanager.model.dto.RespFactory;
import com.carmanager.carmanager.model.dto.Response;
import com.carmanager.carmanager.repository.FeesRepository;
import com.carmanager.carmanager.service.FeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/fees/")
public class FeesController {

    @Autowired
    private FeesService feesService;
    @Autowired
    private FeesRepository feesRepository;

    @RequestMapping(path = "/listfees", method = RequestMethod.GET)
    public List<Fees> listRepairs() {
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

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public ResponseEntity<Fees> getfee(@RequestParam(name = "id") Long id){
        Optional<Fees> fees = feesRepository.findById(id);
        if(fees.isPresent()){
            return RespFactory.result(fees.get());
        }
        return RespFactory.badRequest();
    }

    @RequestMapping(path = "/edit-fee", method = RequestMethod.POST)
    public ResponseEntity<Response> editFee (@RequestBody Fees fee) throws ElementNotFound {

        Optional<Fees> feeId = feesRepository.findById(fee.getId());
        if (!feeId.isPresent()) {
            throw new ElementNotFound();
        }
        feesRepository.saveAndFlush(fee);
        return RespFactory.ok("Repair edited");
    }
    @RequestMapping(path = "/remove-fee/{feeId}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> removeFee(@PathVariable("feeId") Long id) {
        try {
            feesService.removeFee(id);
        } catch (ElementNotFound e) {
            return RespFactory.badRequest();
        }
        return RespFactory.ok("fee deleted");
    }

    @RequestMapping(path = "/add-fee", method = RequestMethod.POST)
    public ResponseEntity<Response> addFee(@RequestBody Fees fees) {

        feesService.addNewFee(fees);
        return RespFactory.created();
    }
}