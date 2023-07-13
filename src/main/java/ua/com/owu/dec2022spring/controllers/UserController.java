package ua.com.owu.dec2022spring.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.com.owu.dec2022spring.dao.UserDAO;
import ua.com.owu.dec2022spring.models.User;
import ua.com.owu.dec2022spring.models.dto.UserDTO;
import ua.com.owu.dec2022spring.models.views.Views;
import ua.com.owu.dec2022spring.services.UserService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
//@AllArgsConstructor
@RequestMapping(value = "/users")
public class UserController {


    //    @Autowired /* = find in bean container instance of userdao and insrt here*/

    private UserService userService;

    public UserController(@Qualifier("one") UserService userService) {
        this.userService = userService;
    }

    //    @PostMapping()
//    @ResponseStatus(HttpStatus.CREATED)
//    public void saveUser(@RequestBody @Valid User user) {
//        userDAO.save(user);
//    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void saveUser(@RequestBody UserDTO userDto) {
        userService.saveUser(userDto);

    }

    @GetMapping()
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return userService.getAllUsers();

    }


    @GetMapping("/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") int userId) {
        return userService.getUserById(userId);
    }


//    @DeleteMapping()
//    public List<User> deleteUser(@RequestParam("id") int userId) {
//        userDAO.deleteById(userId);
//        return userDAO.findAll();
//
//    }
//
//    // /users/level1
//    @GetMapping("/level1")
//    @JsonView(Views.Level1.class)
//    public List<User> getAllUsersForAccessLevel1() {
//        return userDAO.findAll();
//    }

}
