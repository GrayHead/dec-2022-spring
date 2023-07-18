package ua.com.owu.dec2022spring.services.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ua.com.owu.dec2022spring.models.User;
import ua.com.owu.dec2022spring.models.dto.UserDTO;

import java.io.File;
import java.util.List;

@Service
public interface UserService {


    void saveUser(UserDTO userDTO);

    ResponseEntity<User> getUserById(int id);

    ResponseEntity<List<UserDTO>> getAllUsers();

    void save(User user);

    void save(User user, File file);


}
