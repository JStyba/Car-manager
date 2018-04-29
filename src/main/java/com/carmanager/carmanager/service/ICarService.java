package com.carmanager.carmanager.service;

import com.carmanager.carmanager.exceptions.ElementNotFound;
import com.carmanager.carmanager.model.Car;

import java.util.List;

public interface ICarService {
    void addNewCar(Car car);
    void removeCar(Long car) throws ElementNotFound;
    void editCar(Long id);
    List<Car> getAllCars();

}
