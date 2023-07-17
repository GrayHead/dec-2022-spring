package ua.com.owu.dec2022spring.services.car;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.com.owu.dec2022spring.dao.CarDAO;
import ua.com.owu.dec2022spring.models.Car;

import java.util.List;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {
    private CarDAO carDAO;

    @Override
    public ResponseEntity<List<Car>> getAllCars() {

        return new ResponseEntity<>(carDAO.findAll(), HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<Car> getById(int id) {
        if (id <= 0) {
            throw new RuntimeException("id atatatatat");
        }
        return new ResponseEntity<>(carDAO.findById(id).get(), HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<Car> saveCar(Car car) {
        if (car == null) {
            throw new RuntimeException("car atatatatat");
        }
        carDAO.save(car);
        return new ResponseEntity<>(car, HttpStatusCode.valueOf(201));
    }

    @Override
    public void deleteCar(int id) {
        if (id <= 0) {
            throw new RuntimeException("000000000!");
        }
        carDAO.deleteById(id);

    }

    @Override
    public ResponseEntity<List<Car>> getAllCarsByPower(int power) {
        if (power < 56) {
            throw new RuntimeException("power!!! lt 56");
        }
        return new ResponseEntity<>(carDAO.byPower(power), HttpStatusCode.valueOf(200));
    }

    @Override
    public ResponseEntity<List<Car>> getCarsByProducer(String producer) {
        if (producer.length() < 3) {
            throw new RuntimeException("producer");
        }
        return new ResponseEntity<>(carDAO.findByProducer(producer), HttpStatusCode.valueOf(200));
    }
}
