package com.ada.library.controller;

;
import com.ada.library.repository.entity.User;
import com.ada.library.repository.entity.UserDTO;
import com.ada.library.service.UsersServiceMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UsersServiceMap userService;

    @GetMapping
    public ResponseEntity<?> getAllUsers(@RequestParam("dbType") String dbType) {
        switch (dbType.toUpperCase()) {
            case "MONGO":
                List<User> usersM = userService.allMongo();
                List<UserDTO> userDTOsM = usersM.stream().map(UserDTO::userDTO).toList();
                return new ResponseEntity<>(userDTOsM, HttpStatus.OK);
            case "POSTGRES":
                List<User> usersP = userService.allPostgres();
                List<UserDTO> userDTOsP = usersP.stream().map(UserDTO::userDTO).toList();
                return new ResponseEntity<>(userDTOsP, HttpStatus.OK);
            default:
                return new ResponseEntity<>("Database type not supported", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@RequestParam("dbType") String dbType, @PathVariable String id) {
        switch (dbType.toUpperCase()) {
            case "MONGO":
                Optional<User> userM = userService.findByIdMongo(id);
                return userM.map(user -> new ResponseEntity<>(UserDTO.userDTO(user), HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
            case "POSTGRES":
                Long postgresId;
                try {
                    postgresId = Long.parseLong(id);
                } catch (NumberFormatException e) {
                    return new ResponseEntity<>("Invalid ID format for PostgreSQL", HttpStatus.BAD_REQUEST);
                }
                Optional<User> userP = userService.findByIdPostgres(postgresId);
                return userP.map(user -> new ResponseEntity<>(UserDTO.userDTO(user), HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
            default:
                return new ResponseEntity<>("Database type not supported", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestParam("dbType") String dbType, @RequestBody UserDTO userDTO) {
        try {
            User user = User.user(userDTO);
            switch (dbType.toUpperCase()) {
                case "MONGO":
                    User createdUserM = userService.saveMongo(user);
                    return new ResponseEntity<>(UserDTO.userDTO(createdUserM), HttpStatus.CREATED);
                case "POSTGRES":
                    User createdUserP = userService.savePostgres(user);
                    return new ResponseEntity<>(UserDTO.userDTO(createdUserP), HttpStatus.CREATED);
                default:
                    return new ResponseEntity<>("Database type not supported", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating user: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@RequestParam("dbType") String dbType, @PathVariable String id, @RequestBody UserDTO userDTO) {
        try {
            User user = User.user(userDTO);
            switch (dbType.toUpperCase()) {
                case "MONGO":
                    User updatedUserM = userService.updateMongo(user, id);
                    return new ResponseEntity<>(UserDTO.userDTO(updatedUserM), HttpStatus.OK);
                case "POSTGRES":
                    Long postgresId;
                    try {
                        postgresId = Long.parseLong(id);
                    } catch (NumberFormatException e) {
                        return new ResponseEntity<>("Invalid ID format for PostgreSQL", HttpStatus.BAD_REQUEST);
                    }
                    User updatedUserP = userService.updatePostgres(user, postgresId);
                    return new ResponseEntity<>(UserDTO.userDTO(updatedUserP), HttpStatus.OK);
                default:
                    return new ResponseEntity<>("Database type not supported", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating user: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@RequestParam("dbType") String dbType, @PathVariable String id) {
        try {
            switch (dbType.toUpperCase()) {
                case "MONGO":
                    userService.deleteByIdMongo(id);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                case "POSTGRES":
                    Long postgresId;
                    try {
                        postgresId = Long.parseLong(id);
                    } catch (NumberFormatException e) {
                        return new ResponseEntity<>("Invalid ID format for PostgreSQL", HttpStatus.BAD_REQUEST);
                    }
                    userService.deleteByIdPostgres(postgresId);
                    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                default:
                    return new ResponseEntity<>("Database type not supported", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting user: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
