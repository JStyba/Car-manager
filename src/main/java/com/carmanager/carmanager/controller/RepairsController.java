package com.carmanager.carmanager.controller;

import com.carmanager.carmanager.exceptions.ElementNotFound;
import com.carmanager.carmanager.model.Repairs;
import com.carmanager.carmanager.model.dto.RespFactory;
import com.carmanager.carmanager.model.dto.Response;
import com.carmanager.carmanager.repository.RepairsRepository;
import com.carmanager.carmanager.service.RepairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/repairs/")
public class RepairsController {

    @Autowired
    private RepairService repairService;
    @Autowired
    private RepairsRepository repairsRepository;


    @RequestMapping(path = "/listrepairs", method = RequestMethod.GET)
    public List<Repairs> listRepairs() {
        List<Repairs> repairsList = repairService.getAllRepairs().stream()
                .map(repairs -> new Repairs(repairs.getId(),
                        repairs.getName()
                        , repairs.getWorkshop()
                        , repairs.getRepairCost()
                        , repairs.getRepairDate()
                        , repairs.getRepairDescription()))
                .collect(Collectors.toList());
        return repairsList;
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public ResponseEntity<Repairs> getRepair(@RequestParam(name = "id") Long id){
        Optional<Repairs> repairs = repairsRepository.findById(id);
        if(repairs.isPresent()){
            return RespFactory.result(repairs.get());
        }
        return RespFactory.badRequest();
    }

    @RequestMapping(path = "/edit-repair", method = RequestMethod.POST)
    public ResponseEntity<Response> editRepair (@RequestBody Repairs repair) throws ElementNotFound {

        Optional<Repairs> repairId = repairsRepository.findById(repair.getId());
        if (!repairId.isPresent()) {
            throw new ElementNotFound();
        }
        repairsRepository.saveAndFlush(repair);
        return RespFactory.ok("Repair edited");
    }
    @RequestMapping(path = "/remove-repair/{repairId}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> removeRepair(@PathVariable("repairId") Long id) {
        try {
            repairService.removeRepair(id);
        } catch (ElementNotFound e) {
            return RespFactory.badRequest();
        }
        return RespFactory.ok("repair deleted");
    }

    @RequestMapping(path = "/add-repair", method = RequestMethod.POST)
    public ResponseEntity<Response> addRepair(@RequestBody Repairs repairs) {

        repairService.addNewRepair(repairs);
        return RespFactory.created();
    }
}
