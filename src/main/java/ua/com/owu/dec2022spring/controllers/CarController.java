package ua.com.owu.dec2022spring.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.dec2022spring.models.Car;
import ua.com.owu.dec2022spring.models.views.Views;
import ua.com.owu.dec2022spring.services.car.CarService;

import java.util.List;

@RestController
@RequestMapping(value = "/cars")
@AllArgsConstructor
public class CarController {

    private CarService carService;

    @GetMapping()
    @JsonView(Views.Level3.class)
    public ResponseEntity<List<Car>> getAllCars() {
        return carService.getAllCars();
    }

    //    get /cars/{id}
    @GetMapping("/{id}")
    @JsonView(value = Views.Level1.class)
    public ResponseEntity<Car> getById(@PathVariable("id") int id) {
        return carService.getById(id);
    }

    //    post /cars
    @PostMapping()
    public ResponseEntity<Car> saveCar(@RequestBody @Valid Car car) {

        return carService.saveCar(car);
    }

    //    delete /cars/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.GONE)
    public void deleteCar(@PathVariable int id) {
        carService.deleteCar(id);
    }


    @GetMapping("/power/{value}")
    @JsonView(Views.Level2.class)
    public ResponseEntity<List<Car>> getAllCarsByPower(@PathVariable int value) {
        return carService.getAllCarsByPower(value);
    }

    //    get cars/producer/{value} (знайти всі по виробнику)
    @GetMapping("/producer/{value}")
    @JsonView(Views.Level2.class)
    public ResponseEntity<List<Car>> getCarsByProducer(@PathVariable String value) {
        return carService.getCarsByProducer(value);
    }


}


