package Application.Controllers;

import Application.Entities.Car;
import Application.Entities.FuelType;
import Application.Exceptions.CarNotFoundException;
import Application.Service.CarService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Cars", description = "Cars Saled Garage APIs")
@CrossOrigin(origins = "http://localhost:8090")
@RestController
@RequestMapping("/api/v1/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @Operation(
            summary = "Add a cars",
            description = "Add a cars.",
            tags = { "cars", "post" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Tutorial.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PostMapping("/addCars")
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Car savedCar = carService.addCar(car);
        return new ResponseEntity<>(savedCar, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Get All Cars",
            description = "Get All Cars using fuelType and maxPrice.",
            tags = { "cars", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Tutorial.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/fuel-type/{fuelType}/max-price/{maxPrice}")
    public List<Car> getCarsByFuelTypeAndMaxPrice(@PathVariable FuelType fuelType, @PathVariable double maxPrice) {
        return carService.getCarsByFuelTypeAndMaxPrice(fuelType, maxPrice);
    }

    @Operation(
            summary = "Retrieve all makes",
            description = "Get all makes.",
            tags = { "cars", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Tutorial.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @GetMapping("/makes")
    public List<Car> getAllMakes() {
        return carService.getAllMakes();
    }


    @Operation(
            summary = "Update picture",
            description = "Update picture.",
            tags = { "cars", "patch" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Tutorial.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @PatchMapping("/{carId}/update-picture")
    public Car updateCarPicture(@PathVariable Long carId, @RequestParam String picture) throws CarNotFoundException {
        return carService.updateCarPicture(carId, picture);
    }
}
