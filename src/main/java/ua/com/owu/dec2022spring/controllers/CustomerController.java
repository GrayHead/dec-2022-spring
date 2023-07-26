package ua.com.owu.dec2022spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.com.owu.dec2022spring.models.Customer;
import ua.com.owu.dec2022spring.services.CustomerService;

@RestController
@AllArgsConstructor
public class CustomerController {


    private CustomerService customerService;

    @PostMapping("/save")
    public void save(@RequestBody Customer customer) {

        customerService.save(customer);
    }
}
