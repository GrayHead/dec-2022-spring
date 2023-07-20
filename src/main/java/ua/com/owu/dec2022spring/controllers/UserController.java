package ua.com.owu.dec2022spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.owu.dec2022spring.dao.UserDAO;
import ua.com.owu.dec2022spring.models.User;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    private UserDAO userDAO;
    @PostMapping()
    public void saveUser(@RequestBody User user) {
        userDAO.save(user);

    }


}
