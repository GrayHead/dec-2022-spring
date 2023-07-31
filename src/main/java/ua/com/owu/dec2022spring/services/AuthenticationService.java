package ua.com.owu.dec2022spring.services;

import io.jsonwebtoken.Jwt;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.com.owu.dec2022spring.dao.AppUserDAO;
import ua.com.owu.dec2022spring.models.*;

@Service
@AllArgsConstructor
public class AuthenticationService {
    private AppUserDAO appUserDAO;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;


    public AuthenticationResponse register(RegisterRequest registerRequest) {
        AppUser appUser = AppUser
                .builder()
                .firstname(registerRequest.getFirstname())
                .lastname(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        String token = jwtService.generateToken(appUser);
        appUserDAO.save(appUser);

        return AuthenticationResponse
                .builder()
                .token(token)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()

                )
        );
        AppUser appUser = appUserDAO.findAppUserByEmail(authenticationRequest.getEmail());
        String token = jwtService.generateToken(appUser);

        return AuthenticationResponse.builder()
                .token(token)
                .build();

    }
}
