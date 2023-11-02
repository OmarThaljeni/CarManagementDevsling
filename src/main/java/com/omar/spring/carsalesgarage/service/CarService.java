package com.omar.spring.carsalesgarage.service;

import com.omar.spring.carsalesgarage.entities.Car;
import com.omar.spring.carsalesgarage.entities.FuelType;
import com.omar.spring.carsalesgarage.exceptions.CarNotFoundException;

import java.util.List;

public interface CarService {
    Car addCar(Car car);
    List<Car> getCarsByFuelTypeAndMaxPrice(FuelType fuelType, double maxPrice);
    List<Car> getAllMakes();
    Car updateCarPicture(Long carId, String picture) throws CarNotFoundException;
}
