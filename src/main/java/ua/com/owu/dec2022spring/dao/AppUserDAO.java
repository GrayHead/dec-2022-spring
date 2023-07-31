package ua.com.owu.dec2022spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.owu.dec2022spring.models.AppUser;

public interface AppUserDAO extends JpaRepository<AppUser, Integer> {

    AppUser findAppUserByEmail(String email);
}
