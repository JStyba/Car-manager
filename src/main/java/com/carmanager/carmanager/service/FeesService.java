package com.carmanager.carmanager.service;

import com.carmanager.carmanager.exceptions.CarNotFoundException;
import com.carmanager.carmanager.exceptions.ElementNotFound;
import com.carmanager.carmanager.model.AppUser;
import com.carmanager.carmanager.model.Car;
import com.carmanager.carmanager.model.Fees;
import com.carmanager.carmanager.model.dto.AddFeeDto;
import com.carmanager.carmanager.repository.AppUserRepository;
import com.carmanager.carmanager.repository.CarRepository;
import com.carmanager.carmanager.repository.FeesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FeesService implements IFeesService {

    @Autowired
    private FeesRepository feesRepository;
    @Autowired
    private AppUserRepository appUserRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private AppUserService appUserService;

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
    public void addNewFee(AddFeeDto fee) {
        Optional<AppUser> appUser = appUserRepository.findById(fee.getCarOwnerId());
//        Car loggedUserCar = carRepository.findAllByAppUser(fee.getCarOwnerId());
        Optional<Car> car = carRepository.findById(fee.getCarOwnerId());
        if (car.isPresent() && appUser.get().equals(car.get().getAppUser())) {
            Car newCar = car.get();//metoda z optional

            Fees newFee = new Fees( fee.getName()
                    ,fee.getFeeDate()
                    ,fee.getFeeCost()
                    ,fee.getFeeExpirationDate()
                    ,fee.getFeeDescription()
            );
            newFee.setCar(newCar);


            newCar.getFeesSet().add(newFee);
            feesRepository.save(newFee);
            carRepository.save(newCar);
            return;
        }
        throw new CarNotFoundException();
    }

    @Override
    public void addNewEntryInFees(String fees) {
        feesRepository.save(new Fees(fees));
    }
}
