package ua.com.owu.dec2022spring.services;

import ua.com.owu.dec2022spring.models.Customer;


public interface CustomerService {
    void save(Customer customer);

    Customer findByUsername(String username);

}
