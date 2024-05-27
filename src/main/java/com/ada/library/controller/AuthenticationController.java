package com.ada.library.controller;

import com.ada.library.config.jwt.JwtUtil;
import com.ada.library.dto.AuthenticationRequest;
import com.ada.library.dto.AuthenticationResponse;
import com.ada.library.repository.entity.UserDetailsImp;
import com.ada.library.service.impl.UserServiceDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private UserServiceDetails userServiceDetails;


    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
        );

        final UserDetailsImp userDetails = userServiceDetails
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }


    @PostMapping("/validateUser")
    public ResponseEntity<?> validateUser(@RequestBody AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())

            );
            return ResponseEntity.ok("Usuario validado exitosamente.");
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos.");
        }
    }

    @PostMapping("/generateCustomToken")
    public ResponseEntity<?> generateCustomToken(@RequestBody AuthenticationRequest authenticationRequest) {
        UserDetailsImp userDetails = userServiceDetails.loadUserByUsername(authenticationRequest.getUsername());
        String customToken = generateCustomTokenForUser(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(customToken));
    }

    private String generateCustomTokenForUser(UserDetailsImp userDetails) {
        String customToken = Base64.getEncoder().encodeToString((userDetails.getUsername() + ":" + System.currentTimeMillis()).getBytes());
        return customToken;
    }
}
