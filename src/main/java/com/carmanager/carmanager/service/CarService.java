package com.carmanager.carmanager.service;

import com.carmanager.carmanager.exceptions.ElementNotFound;
import com.carmanager.carmanager.exceptions.UserNotFoundException;
import com.carmanager.carmanager.model.AppUser;
import com.carmanager.carmanager.model.Car;
import com.carmanager.carmanager.model.dto.AddCarDto;
import com.carmanager.carmanager.repository.AppUserRepository;
import com.carmanager.carmanager.repository.CarRepository;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarService implements ICarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public void addNewCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public void addNewCar(AddCarDto car) {
        Optional<AppUser> appUser = appUserRepository.findById(car.getOwnerId());
        if (appUser.isPresent()) {
            AppUser user = appUser.get();//metoda z optional

            Car newCar = new Car(car.getFuelType(),
                    car.getMake(),
                    car.getModel(),
                    car.getDateProduced(),
                    car.getVin(),
                    car.getEngineCapacity(),
                    car.getNumberOfSeats(),
                    car.getRegistrationNumber());
            newCar.setAppUser(user);

            user.getCar().add(newCar);
            carRepository.save(newCar);
            appUserRepository.save(user);
            return;
        }
        throw new UserNotFoundException();
    }

    @Override
    public void removeCar(Long id) throws ElementNotFound {
        carRepository.deleteById(id);
    }

    @Override
    public void editCar(Long carId) {
        carRepository.findById(carId);

    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }


    @Override
    public List<Car> getAllCars(Long userId) {
        Optional<AppUser> appUser = appUserRepository.findById(userId);
        if (appUser.isPresent()) {
            return carRepository.findAllByAppUser(appUser.get());
        }
        // TODO: albo exception
        return new ArrayList<>();
    }
}
