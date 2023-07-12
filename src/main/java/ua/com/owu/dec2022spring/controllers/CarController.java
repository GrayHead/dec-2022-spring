package ua.com.owu.dec2022spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.dec2022spring.dao.CarDAO;
import ua.com.owu.dec2022spring.models.Car;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/cars")
@AllArgsConstructor
public class CarController {

    private CarDAO carDAO;

    @GetMapping()
    public ResponseEntity<List<Car>> getAllCars() {
        return new ResponseEntity<>(carDAO.findAll(), HttpStatusCode.valueOf(200));
    }

    //    get /cars/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Car> getById(@PathVariable("id") int id) {
        return new ResponseEntity<>(carDAO.findById(id).get(), HttpStatusCode.valueOf(200));
    }

    //    post /cars
    @PostMapping()
    public ResponseEntity<Car> saveCar(@RequestBody Car car) { // id 0
        carDAO.save(car); // id =1
        return new ResponseEntity<>(car, HttpStatusCode.valueOf(201));
    }

    //    delete /cars/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.GONE)
    public void deleteCar(@PathVariable int id) {
        carDAO.deleteById(id);
    }

    //    get cars/power/{value} (знайти всі по потужності)\
    @GetMapping("/power/{value}")
    public ResponseEntity<List<Car>> getAllCarsByPower(@PathVariable int value) {
        //#1
//        List<Car> collect = carDAO.findAll().stream().filter(car -> car.getPower() == value).collect(Collectors.toList());
//        return new ResponseEntity<>(collect, HttpStatusCode.valueOf(200));
        //#2
//        return new ResponseEntity<>(carDAO.customFindCarsByPower(value), HttpStatusCode.valueOf(200));
        //#3
        return new ResponseEntity<>(carDAO.findByPower(value), HttpStatusCode.valueOf(200));
    }

    //    get cars/producer/{value} (знайти всі по виробнику)
    @GetMapping("/producer/{value}")
    public ResponseEntity<List<Car>> getCarsByPeoducer(@PathVariable String value) {
        return new ResponseEntity<>(carDAO.findByProducer(value), HttpStatusCode.valueOf(200));
    }


}
