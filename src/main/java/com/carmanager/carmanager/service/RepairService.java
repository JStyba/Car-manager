package com.carmanager.carmanager.service;

import com.carmanager.carmanager.exceptions.CarNotFoundException;
import com.carmanager.carmanager.exceptions.ElementNotFound;
import com.carmanager.carmanager.model.AppUser;
import com.carmanager.carmanager.model.Car;
import com.carmanager.carmanager.model.Repairs;
import com.carmanager.carmanager.model.dto.AddRepairDto;
import com.carmanager.carmanager.repository.AppUserRepository;
import com.carmanager.carmanager.repository.CarRepository;
import com.carmanager.carmanager.repository.RepairsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RepairService implements IRepairService {

    @Autowired
    private RepairsRepository repairsRepository;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private AppUserService appUserService;

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

    @Override
    public void addNewRepair(AddRepairDto repair) {
        Optional<AppUser> appUser = appUserRepository.findById(repair.getCarOwnerId());
//        Car loggedUserCar = carRepository.findAllByAppUser(fee.getCarOwnerId());
        Optional<Car> car = carRepository.findById(repair.getCarId());
        if (car.isPresent()) {
            Car newCar = car.get();//metoda z optional
            if (appUser.isPresent()) {
                AppUser user = appUser.get();
                if (user.getId().equals(newCar.getAppUser().getId())) {
                    Repairs newRepair = new Repairs(repair.getName()
                            , repair.getWorkshop()
                            , repair.getRepairCost()
                            , repair.getRepairDate()
                            , repair.getRepairDescription()
                    );
                    newRepair.setCar(newCar);
                    newCar.getRepairsSet().add(newRepair);
                    repairsRepository.save(newRepair);
                    carRepository.save(newCar);
                    return;
                }
            }
        }
        throw new CarNotFoundException();
    }
}
