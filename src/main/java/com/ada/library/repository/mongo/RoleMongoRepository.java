package com.ada.library.repository.mongo;

import com.ada.library.repository.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMongoRepository extends MongoRepository<Role, String> {
}
