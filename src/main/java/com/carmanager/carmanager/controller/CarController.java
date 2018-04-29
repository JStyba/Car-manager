package com.carmanager.carmanager.controller;

import com.carmanager.carmanager.exceptions.ElementNotFound;
import com.carmanager.carmanager.model.Car;
import com.carmanager.carmanager.model.Expenses;
import com.carmanager.carmanager.model.dto.AddCarDto;
import com.carmanager.carmanager.model.dto.RespFactory;
import com.carmanager.carmanager.model.dto.Response;
import com.carmanager.carmanager.repository.CarRepository;
import com.carmanager.carmanager.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@CrossOrigin
@RestController
@RequestMapping("/cars/")
public class CarController {

    @Autowired
    private CarService carService;
    @Autowired
    private CarRepository carRepository;

    @RequestMapping(path = "/list-cars", method = RequestMethod.GET)
    public List<Car> listExpenses() {
        List<Car> carList = carService.getAllCars();
        return carList;
    }
    @RequestMapping(path = "/list-user-cars", method = RequestMethod.GET)
    public List<Car> listUserCars(@RequestParam(name="userId") Long id) {
        List<Car> carList = carService.getAllCars(id);
        return carList;
    }

    @RequestMapping(path = "/get", method = RequestMethod.GET)
    public ResponseEntity<Car> getExpense(@RequestParam(name = "carId") Long id) {
        Optional<Car> car = carRepository.findById(id);
        if (car.isPresent()) {
            return RespFactory.result(car.get());
        }
        return RespFactory.badRequest();
    }

    @RequestMapping(path = "/remove-car/{carId}", method = RequestMethod.DELETE)
    public ResponseEntity<Response> removeCar(@PathVariable("carId") Long id) {
        try {
            carService.removeCar(id);
        } catch (ElementNotFound e) {
            return RespFactory.badRequest();
        }
        return RespFactory.ok("expense deleted");
    }

    @RequestMapping(path = "/add-car", method = RequestMethod.POST)
    public ResponseEntity<Response> addCar(@RequestBody Car car) {
        carService.addNewCar(car);
        return RespFactory.created();
    }

    @RequestMapping(path = "/add-user-car", method = RequestMethod.POST)
    public ResponseEntity<Response> addCar(@RequestBody AddCarDto car) {
        carService.addNewCar(car);
        return RespFactory.created();
    }

    @RequestMapping(path = "/edit-car", method = RequestMethod.POST)
    public ResponseEntity<Response> editCar(@RequestBody Car car) throws ElementNotFound {

        Optional<Car> carId = carRepository.findById(car.getId());
        if (!carId.isPresent()) {
            throw new ElementNotFound();
        }
        carRepository.saveAndFlush(car);
        return RespFactory.ok("Car edited");
    }
}
