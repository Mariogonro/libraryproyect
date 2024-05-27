package com.ada.library.service;

import com.ada.library.repository.entity.User;

import java.util.*;

public interface UserService {

    User savePostgres(User user);

    Optional<User> findByIdPostgres(Long id);

    List<User> allPostgres();

    void deleteByIdPostgres(Long id);

    User updatePostgres(User user, Long id);

    User saveMongo(User user);

    Optional<User> findByIdMongo(String id);

    List<User> allMongo();

    void deleteByIdMongo(String id);

    User updateMongo(User user, String id);
}
