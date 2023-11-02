package com.omar.spring.carsalesgarage;

import com.omar.spring.carsalesgarage.entities.Car;
import com.omar.spring.carsalesgarage.entities.FuelType;
import com.omar.spring.carsalesgarage.entities.TransmissionType;
import com.omar.spring.carsalesgarage.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class StarterTests {

	@Autowired
	private CarService carService;

	@Test
	public void testAddCar() throws ParseException {
		// Create a sample car
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Car car = new Car();
		car.setMake("Toyota");
		car.setModel("Camry");
		car.setPrice(25000.0);
		car.setFuelType(FuelType.HYBRID);
		car.setMileage(15000);
		car.setRegistrationDate(dateFormat.parse("2022-01-01"));
		car.setTransmission(TransmissionType.AUTOMATIC);
		car.setPicture("car.jpg");
		// Test adding the car
		Car addedCar = carService.addCar(car);

		// Verify the car is added successfully
		assertThat(addedCar.getId()).isNotNull();
		assertThat(addedCar.getMake()).isEqualTo("Toyota");
		// Add more assertions as needed
	}

}
