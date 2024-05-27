package com.ada.library.service;

import com.ada.library.repository.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    //Postgres
    Book savePostgres(Book book);

    Optional<Book> findByIdPostgres(Long id);

    List<Book> allPostgres();

    void deleteByIdPostgres(Long id);

    Book updatePostgres(Book book, Long id);

    //Mongo
    Book saveMongo(Book book);

    Optional<Book> findByIdMongo(String id);

    List<Book> allMongo();

    void deleteByIdMongo(String id);

    Book updateMongo(Book book, String id);
}
