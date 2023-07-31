package ua.com.owu.dec2022spring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.com.owu.dec2022spring.models.AuthenticationRequest;
import ua.com.owu.dec2022spring.models.AuthenticationResponse;
import ua.com.owu.dec2022spring.models.RegisterRequest;
import ua.com.owu.dec2022spring.services.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationService authenticationService;


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse>  authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
            return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}
