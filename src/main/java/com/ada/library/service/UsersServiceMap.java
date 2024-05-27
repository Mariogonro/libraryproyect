package com.ada.library.service;

import com.ada.library.repository.mongo.UserMongoRepository;
import com.ada.library.repository.postgres.UserPostgresRepository;
import com.ada.library.repository.entity.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsersServiceMap implements UserService {
    private final UserMongoRepository mongoRepository;
    private final UserPostgresRepository postgresRepository;

    public UsersServiceMap(UserMongoRepository mongoRepository, UserPostgresRepository postgresRepository) {
        this.mongoRepository = mongoRepository;
        this.postgresRepository = postgresRepository;
    }

    @Override
    public User savePostgres(User user) {
        return postgresRepository.save(user);
    }

    @Override
    public Optional<User> findByIdPostgres(Long id) {
        return postgresRepository.findById(id);
    }

    @Override
    public List<User> allPostgres() {
        return postgresRepository.findAll();
    }

    @Override
    public void deleteByIdPostgres(Long id) {
        postgresRepository.deleteById(id);
    }

    @Override
    public User updatePostgres(User user, Long id) {
        user.setId(id);
        return postgresRepository.save(user);
    }

    @Override
    public User saveMongo(User user) {
        return mongoRepository.save(user);
    }

    @Override
    public Optional<User> findByIdMongo(String id) {
        return mongoRepository.findById(id);
    }

    @Override
    public List<User> allMongo() {
        return mongoRepository.findAll();
    }

    @Override
    public void deleteByIdMongo(String id) {
        mongoRepository.deleteById(id);
    }

    @Override
    public User updateMongo(User user, String id) {
        user.setIdMongo(id);
        return mongoRepository.save(user);
    }
}
