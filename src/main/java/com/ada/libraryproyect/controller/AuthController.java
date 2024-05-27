package com.ada.libraryproyect.controller;

import com.ada.libraryproyect.dto.ApiResponseDto;
import com.ada.libraryproyect.dto.SignInRequestDto;
import com.ada.libraryproyect.dto.SignUpRequestDto;
import com.ada.libraryproyect.handler.exception.RoleNotFoundException;
import com.ada.libraryproyect.handler.exception.UserAlreadyExistsException;
import com.ada.libraryproyect.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto<?>> registerUser(@RequestBody @Valid SignUpRequestDto signUpRequestDto)
            throws UserAlreadyExistsException, RoleNotFoundException {
        return authService.signUpUser(signUpRequestDto);
    }

    @PostMapping("/signin")
    public ResponseEntity<ApiResponseDto<?>> signInUser(@RequestBody SignInRequestDto signInRequestDto){
        return authService.signInUser(signInRequestDto);
    }

}