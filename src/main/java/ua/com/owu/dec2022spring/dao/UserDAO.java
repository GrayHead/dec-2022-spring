package ua.com.owu.dec2022spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import ua.com.owu.dec2022spring.models.User;

public interface UserDAO extends MongoRepository<User, String> {
    // CRUD
}
