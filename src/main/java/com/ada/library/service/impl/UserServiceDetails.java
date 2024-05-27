package com.ada.library.service.impl;


import com.ada.library.repository.entity.User;
import com.ada.library.repository.entity.UserDetailsImp;
import com.ada.library.repository.mongo.UserMongoRepository;
import com.ada.library.repository.postgres.UserPostgresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceDetails implements UserDetailsService {

    @Autowired
    private UserPostgresRepository userPostgresRepository;

    @Autowired
    private UserMongoRepository userMongoRepository;

    @Override
    public UserDetailsImp loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userPostgresRepository.findByUsername(username);

        if (user == null) {
            user = userMongoRepository.findByUsername(username);
        }

        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        return new UserDetailsImp(user);
    }
}