package com.ada.library.repository.mongo;

import com.ada.library.repository.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMongoRepository extends MongoRepository<Book, String> {
}
