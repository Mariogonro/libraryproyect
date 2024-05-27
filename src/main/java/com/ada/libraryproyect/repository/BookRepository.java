package com.ada.libraryproyect.repository;

import com.ada.libraryproyect.repository.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface BookRepository extends MongoRepository<Book, String> {

}
