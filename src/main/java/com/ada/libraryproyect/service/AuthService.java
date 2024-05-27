package com.ada.libraryproyect.service;

import com.ada.libraryproyect.dto.ApiResponseDto;
import com.ada.libraryproyect.dto.SignInRequestDto;
import com.ada.libraryproyect.dto.SignUpRequestDto;
import com.ada.libraryproyect.handler.exception.RoleNotFoundException;
import com.ada.libraryproyect.handler.exception.UserAlreadyExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    ResponseEntity<ApiResponseDto<?>> signUpUser(SignUpRequestDto signUpRequestDto) throws UserAlreadyExistsException, RoleNotFoundException;
    ResponseEntity<ApiResponseDto<?>> signInUser(SignInRequestDto signInRequestDto);
}