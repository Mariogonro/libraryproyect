package com.ada.library.service.impl;

import com.ada.library.dto.UserDto;
import com.ada.library.repository.entity.User;
import com.ada.library.repository.mongo.UserMongoRepository;
import com.ada.library.repository.postgres.UserPostgresRepository;
import com.ada.library.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserPostgresRepository userPostgresRepository;

    @Autowired
    private UserMongoRepository userMongoRepository;

    @Override
    public UserDto createUser(UserDto userDto, String dbType) {
        User user = User.fromEntity(userDto);
        switch (dbType.toUpperCase()) {
            case "POSTGRES":
                user = userPostgresRepository.save(user);
                break;
            case "MONGO":
                user = userMongoRepository.save(user);
                break;
            default:
                throw new IllegalArgumentException("Invalid database type");
        }
        return User.toEntity(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, String dbType) {
        User user = User.fromEntity(userDto);
        switch (dbType.toUpperCase()) {
            case "POSTGRES":
                if (userPostgresRepository.existsById(user.getId())) {
                    user = userPostgresRepository.save(user);
                } else {
                    throw new RuntimeException("User not found");
                }
                break;
            case "MONGO":
                if (userMongoRepository.existsById(user.getIdMongo())) {
                    user = userMongoRepository.save(user);
                } else {
                    throw new RuntimeException("User not found");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid database type");
        }
        return User.toEntity(user);
    }

    @Override
    public void deleteUser(String userId, String dbType) {
        switch (dbType.toUpperCase()) {
            case "POSTGRES":
                Long id = Long.parseLong(userId);
                userPostgresRepository.deleteById(id);
                break;
            case "MONGO":
                userMongoRepository.deleteById(userId);
                break;
            default:
                throw new IllegalArgumentException("Invalid database type");
        }
    }

    @Override
    public Optional<UserDto> getUserById(String userId, String dbType) {
        Optional<User> user;
        switch (dbType.toUpperCase()) {
            case "POSTGRES":
                Long id = Long.parseLong(userId);
                user = userPostgresRepository.findById(id);
                break;
            case "MONGO":
                user = userMongoRepository.findById(userId);
                break;
            default:
                throw new IllegalArgumentException("Invalid database type");
        }
        return user.map(User::toEntity);
    }

    @Override
    public List<UserDto> getAllUsers(String dbType) {
        List<User> users;
        switch (dbType.toUpperCase()) {
            case "POSTGRES":
                users = userPostgresRepository.findAll();
                break;
            case "MONGO":
                users = userMongoRepository.findAll();
                break;
            default:
                throw new IllegalArgumentException("Invalid database type");
        }
        return users.stream().map(User::toEntity).collect(Collectors.toList());
    }

    @Override
    public UserDto findUserByUsername(String username, String dbType) {
        User user;
        switch (dbType.toUpperCase()) {
            case "POSTGRES":
                user = userPostgresRepository.findByUsername(username);
                break;
            case "MONGO":
                user = userMongoRepository.findByUsername(username);
                break;
            default:
                throw new IllegalArgumentException("Invalid database type");
        }
        return user != null ? User.toEntity(user) : null;
    }
}