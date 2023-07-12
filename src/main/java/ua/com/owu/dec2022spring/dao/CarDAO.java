package ua.com.owu.dec2022spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.com.owu.dec2022spring.models.Car;

import java.util.List;

public interface CarDAO extends JpaRepository<Car, Integer> {

    @Query("select c from Car c where c.power=:xxx")
    List<Car> customFindCarsByPower(@Param("xxx") int power);

    List<Car> findByPower(int power);

    List<Car> findByProducer(String producer);
}
