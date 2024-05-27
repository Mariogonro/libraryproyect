package com.ada.library.service.impl;

import com.ada.library.dto.UserBookActionDto;
import com.ada.library.repository.entity.UserBookAction;
import com.ada.library.repository.mongo.UserBookActionMongoRepository;
import com.ada.library.repository.postgres.UserBookActionPostgresRepository;
import com.ada.library.service.IUserBookActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserBookActionService implements IUserBookActionService {

    @Autowired
    private UserBookActionPostgresRepository userBookActionPostgresRepository;

    @Autowired
    private UserBookActionMongoRepository userBookActionMongoRepository;

    @Override
    public UserBookActionDto createUserBookAction(UserBookActionDto userBookActionDto, String dbType) {
        UserBookAction userBookAction = UserBookAction.fromEntity(userBookActionDto);
        switch (dbType.toUpperCase()) {
            case "POSTGRES":
                userBookAction = userBookActionPostgresRepository.save(userBookAction);
                break;
            case "MONGO":
                userBookAction = userBookActionMongoRepository.save(userBookAction);
                break;
            default:
                throw new IllegalArgumentException("Invalid database type");
        }
        return UserBookAction.toEntity(userBookAction);
    }

    @Override
    public UserBookActionDto updateUserBookAction(UserBookActionDto userBookActionDto, String dbType) {
        UserBookAction userBookAction = UserBookAction.fromEntity(userBookActionDto);
        switch (dbType.toUpperCase()) {
            case "POSTGRES":
                if (userBookActionPostgresRepository.existsById(userBookAction.getId())) {
                    userBookAction = userBookActionPostgresRepository.save(userBookAction);
                } else {
                    throw new RuntimeException("User book action not found");
                }
                break;
            case "MONGO":
                if (userBookActionMongoRepository.existsById(userBookAction.getIdMongo())) {
                    userBookAction = userBookActionMongoRepository.save(userBookAction);
                } else {
                    throw new RuntimeException("User book action not found");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid database type");
        }
        return UserBookAction.toEntity(userBookAction);
    }

    @Override
    public void deleteUserBookAction(String userBookActionId, String dbType) {
        switch (dbType.toUpperCase()) {
            case "POSTGRES":
                Long id = Long.parseLong(userBookActionId);
                userBookActionPostgresRepository.deleteById(id);
                break;
            case "MONGO":
                userBookActionMongoRepository.deleteById(userBookActionId);
                break;
            default:
                throw new IllegalArgumentException("Invalid database type");
        }
    }

    @Override
    public Optional<UserBookActionDto> getUserBookActionById(String userBookActionId, String dbType) {
        Optional<UserBookAction> userBookAction;
        switch (dbType.toUpperCase()) {
            case "POSTGRES":
                Long id = Long.parseLong(userBookActionId);
                userBookAction = userBookActionPostgresRepository.findById(id);
                break;
            case "MONGO":
                userBookAction = userBookActionMongoRepository.findById(userBookActionId);
                break;
            default:
                throw new IllegalArgumentException("Invalid database type");
        }
        return userBookAction.map(UserBookAction::toEntity);
    }

    @Override
    public List<UserBookActionDto> getAllUserBookActions(String dbType) {
        List<UserBookAction> userBookActions;
        switch (dbType.toUpperCase()) {
            case "POSTGRES":
                userBookActions = userBookActionPostgresRepository.findAll();
                break;
            case "MONGO":
                userBookActions = userBookActionMongoRepository.findAll();
                break;
            default:
                throw new IllegalArgumentException("Invalid database type");
        }
        return userBookActions.stream().map(UserBookAction::toEntity).collect(Collectors.toList());
    }
}

