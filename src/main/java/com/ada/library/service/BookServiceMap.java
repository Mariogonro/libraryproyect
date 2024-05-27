package com.ada.library.service;

import com.ada.library.repository.mongo.BookMongoRepository;
import com.ada.library.repository.postgres.BookPostgresRepository;
import com.ada.library.repository.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceMap implements BookService {
    private final BookMongoRepository mongoRepository;
    private final BookPostgresRepository postgresRepository;

    public BookServiceMap(BookMongoRepository mongoRepository, BookPostgresRepository postgresRepository) {
        this.mongoRepository = mongoRepository;
        this.postgresRepository = postgresRepository;
    }

    @Override
    public Book savePostgres(Book book) {
        return postgresRepository.save(book);
    }

    @Override
    public Optional<Book> findByIdPostgres(Long id) {
        return postgresRepository.findById(id);
    }

    @Override
    public List<Book> allPostgres() {
        return postgresRepository.findAll();
    }

    @Override
    public void deleteByIdPostgres(Long id) {
        postgresRepository.deleteById(id);
    }

    @Override
    public Book updatePostgres(Book book, Long id) {
        book.setId(id);
        return postgresRepository.save(book);
    }

    @Override
    public Book saveMongo(Book book) {
        return mongoRepository.save(book);
    }

    @Override
    public Optional<Book> findByIdMongo(String id) {
        return mongoRepository.findById(id);
    }

    @Override
    public List<Book> allMongo() {
        return mongoRepository.findAll();
    }

    @Override
    public void deleteByIdMongo(String id) {
        mongoRepository.deleteById(id);
    }

    @Override
    public Book updateMongo(Book book, String id) {
        book.setIdMongo(id);
        return mongoRepository.save(book);
    }
}
