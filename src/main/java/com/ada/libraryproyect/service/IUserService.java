package com.ada.libraryproyect.service;


import com.ada.libraryproyect.repository.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {

    List<User> getAllUsers();
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    void save(User user);

}
