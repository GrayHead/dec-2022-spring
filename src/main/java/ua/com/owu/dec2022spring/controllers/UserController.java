package ua.com.owu.dec2022spring.controllers;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ua.com.owu.dec2022spring.models.User;
import ua.com.owu.dec2022spring.models.dto.UserDTO;
import ua.com.owu.dec2022spring.services.user.UserService;

import java.io.File;
import java.util.List;

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


    @SneakyThrows
    @PostMapping("/savewithavatar")
    public void saveWithAvatar(@RequestParam("avatar") MultipartFile avatar,
                               @RequestParam("name") String name,
                               @RequestParam String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        String originalFilename = avatar.getOriginalFilename(); // homer.jpg
        user.setAvatar(originalFilename); // {name:kokos,avatar:homer.jpg}

//        System.out.println(avatar.getResource().getFile().getPath());

        String path = System.getProperty("user.home") + File.separator + "qqq" + File.separator + originalFilename;
        File transferDestinationFile = new File(path);
        avatar.transferTo(transferDestinationFile);
        userService.save(user, transferDestinationFile);
    }


    @GetMapping("/activate/{id}")
    public void activateUser(@PathVariable int id) {
        ResponseEntity<User> userById = userService.getUserById(id);
        User user = userById.getBody();
        user.setActivated(true);
        userService.save(user);

    }

    @PostMapping("/saveWithEmail")
    public void saveWithEmail(@RequestBody User user) {
        userService.save(user);

    }
}
