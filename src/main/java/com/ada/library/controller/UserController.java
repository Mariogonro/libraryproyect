package com.ada.library.controller;

;
import com.ada.library.dto.UserDto;
import com.ada.library.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers(@RequestParam String dbType) {
        List<UserDto> users = userService.getAllUsers(dbType);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String userId, @RequestParam String dbType) {
        return userService.getUserById(userId, dbType)
                .map(userDto -> new ResponseEntity<>(userDto, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto, @RequestParam String dbType) {
        UserDto createdUser = userService.createUser(userDto, dbType);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String userId, @RequestBody UserDto userDto, @RequestParam String dbType) {
        userDto.setId(userId);
        UserDto updatedUser = userService.updateUser(userDto, dbType);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId, @RequestParam String dbType) {
        userService.deleteUser(userId, dbType);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
