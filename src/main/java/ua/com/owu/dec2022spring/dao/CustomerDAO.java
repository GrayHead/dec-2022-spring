package ua.com.owu.dec2022spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.owu.dec2022spring.models.Customer;

public interface CustomerDAO extends JpaRepository<Customer, Integer> {
    Customer findByUsername(String username);
}
