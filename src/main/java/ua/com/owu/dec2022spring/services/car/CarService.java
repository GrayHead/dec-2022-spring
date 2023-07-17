package ua.com.owu.dec2022spring.services.car;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.com.owu.dec2022spring.models.Car;

import java.util.List;


public interface CarService {

    ResponseEntity<List<Car>> getAllCars();

    ResponseEntity<Car> getById(int id);

    ResponseEntity<Car> saveCar(Car car);

    void deleteCar(int id);

    ResponseEntity<List<Car>> getAllCarsByPower(int power);

    ResponseEntity<List<Car>> getCarsByProducer(String producer);

}
