package com.ada.libraryproyect.repository;

import com.ada.libraryproyect.repository.entity.UserBook;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserBookRepository extends MongoRepository<UserBook, String> {
}
