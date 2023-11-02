package com.omar.spring.carsalesgarage.service;


import com.omar.spring.carsalesgarage.entities.FuelType;
import com.omar.spring.carsalesgarage.repositories.CarRepository;
import com.omar.spring.carsalesgarage.entities.Car;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;


@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public Car addCar(Car car) {
        if (car.getRegistrationDate().after(Date.from(Instant.parse("2015-01-01T00:00:00Z")))) {
            return carRepository.save(car);
        } else {
            throw new IllegalArgumentException("Cars registered before 2015 are not allowed.");
        }
    }

    @Override
    public List<Car> getCarsByFuelTypeAndMaxPrice(FuelType fuelType, double maxPrice) {
        Date date2015 = Date.from(Instant.parse("2015-01-01T00:00:00Z"));
        return carRepository.findByRegistrationDateAfterAndFuelTypeAndPriceLessThanEqual(date2015, fuelType, maxPrice);
    }
    @Override
    public List<Car> getAllMakes() {
        return carRepository.findAll();
    }
    @Override
    public Car updateCarPicture(Long carId, String pictureUrl) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new EntityNotFoundException("Car not found"));
        car.setPicture(pictureUrl);
        return carRepository.save(car);
    }

}
