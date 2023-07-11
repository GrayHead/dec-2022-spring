package ua.com.owu.dec2022spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.owu.dec2022spring.models.User;

public interface UserDAO extends JpaRepository<User, Integer> {
    // CRUD
}
