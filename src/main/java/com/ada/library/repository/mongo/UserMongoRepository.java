package com.ada.library.repository.mongo;

import com.ada.library.repository.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMongoRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
}


