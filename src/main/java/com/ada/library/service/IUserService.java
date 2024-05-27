package com.ada.library.service;

import com.ada.library.dto.UserDto;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    UserDto createUser(UserDto userDto, String dbType);

    UserDto updateUser(UserDto userDto, String dbType);

    void deleteUser(String userId, String dbType);

    Optional<UserDto> getUserById(String userId, String dbType);

    List<UserDto> getAllUsers(String dbType);

    UserDto findUserByUsername(String username, String dbType);
}