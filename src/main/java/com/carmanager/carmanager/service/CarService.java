package com.carmanager.carmanager.service;

import com.carmanager.carmanager.model.Car;
import com.carmanager.carmanager.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CarService implements ICarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public void addNewCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public void removeCar(Car car) {
        carRepository.delete(car);
    }

    @Override
    public void editCar(Long carId) {
    carRepository.findById(carId);

    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }
}
