package com.ada.library.repository.mongo;

import com.ada.library.repository.entity.UserBookAction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBookActionMongoRepository extends MongoRepository<UserBookAction, String> {
}
