package com.omar.spring.carsalesgarage.controller;

import com.omar.spring.carsalesgarage.entities.Car;
import com.omar.spring.carsalesgarage.entities.FuelType;
import com.omar.spring.carsalesgarage.exceptions.CarNotFoundException;
import com.omar.spring.carsalesgarage.service.CarService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cars", description = "Cars management APIs")
@RestController
@RequestMapping("/api/cars")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CarController {

    @Autowired
    private CarService carService;

    @Operation(summary = "Create a new car", tags = { "cars", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = Car.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/update-cars")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Car savedCar = carService.addCar(car);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get All cars",
            description = "Retrieve All cars.",
            tags = { "Cars", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Car.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/fuel-type/{fuelType}/max-price/{maxPrice}")
    public List<Car> getCarsByFuelTypeAndMaxPrice(@PathVariable FuelType fuelType, @PathVariable double maxPrice) {
        return carService.getCarsByFuelTypeAndMaxPrice(fuelType, maxPrice);
    }

    @Operation(
            summary = "Retrieve a cars by makes",
            description = "Retrieve a cars by makes object with id, title, description and published status.",
            tags = { "Cars", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Car.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/makes")
    public List<Car> getAllMakes() {
        return carService.getAllMakes();
    }

    @Operation(summary = "Update an existant car", tags = { "cars", "patch" })
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {
                    @Content(schema = @Schema(implementation = Car.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PatchMapping("/{carId}/update-picture")
    public Car updateCarPicture(@PathVariable Long carId, @RequestParam String picture) throws CarNotFoundException {
        return carService.updateCarPicture(carId, picture);
    }
}
