package ua.com.owu.dec2022spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.dec2022spring.dao.UserDAO;
import ua.com.owu.dec2022spring.models.User;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/users")
public class UserController {


    //    @Autowired /* = find in bean container instance of userdao and insrt here*/
    private UserDAO userDAO;


    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody User user) {
        userDAO.save(user);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("customHeader", "custom value");
        return new ResponseEntity<>(userDAO.findAll(), httpHeaders, HttpStatus.ACCEPTED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") int userId) {
        return new ResponseEntity<>(userDAO.findById(userId).get(), HttpStatusCode.valueOf(200));
    }


    @DeleteMapping()
    public List<User> deleteUser(@RequestParam("id") int userId) {
        userDAO.deleteById(userId);
        return userDAO.findAll();

    }

}
