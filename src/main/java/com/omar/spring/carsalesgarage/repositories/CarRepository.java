package com.omar.spring.carsalesgarage.repositories;


import com.omar.spring.carsalesgarage.entities.Car;
import com.omar.spring.carsalesgarage.entities.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByRegistrationDateAfterAndFuelTypeAndPriceLessThanEqual(Date registrationDate, FuelType fuelType, double price);
}
