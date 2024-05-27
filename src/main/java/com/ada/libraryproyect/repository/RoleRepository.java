package com.ada.libraryproyect.repository;

import com.ada.libraryproyect.constants.ERole;
import com.ada.libraryproyect.repository.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {
    Role findByName(ERole name);
}
