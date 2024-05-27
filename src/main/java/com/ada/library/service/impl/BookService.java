package com.ada.library.service.impl;

import com.ada.library.dto.BookDto;
import com.ada.library.repository.entity.Book;
import com.ada.library.repository.mongo.BookMongoRepository;
import com.ada.library.repository.postgres.BookPostgresRepository;
import com.ada.library.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookPostgresRepository bookPostgresRepository;

    @Autowired
    private BookMongoRepository bookMongoRepository;

    @Override
    public BookDto createBook(BookDto bookDto, String dbType) {
        Book book = Book.fromEntity(bookDto);
        switch (dbType.toUpperCase()) {
            case "POSTGRES":
                book = bookPostgresRepository.save(book);
                break;
            case "MONGO":
                book = bookMongoRepository.save(book);
                break;
            default:
                throw new IllegalArgumentException("Invalid database type");
        }
        return Book.toEntity(book);
    }

    @Override
    public BookDto updateBook(BookDto bookDto, String dbType) {
        Book book = Book.fromEntity(bookDto);
        switch (dbType.toUpperCase()) {
            case "POSTGRES":
                if (bookPostgresRepository.existsById(book.getId())) {
                    book = bookPostgresRepository.save(book);
                } else {
                    throw new RuntimeException("Book not found");
                }
                break;
            case "MONGO":
                if (bookMongoRepository.existsById(book.getIdMongo())) {
                    book = bookMongoRepository.save(book);
                } else {
                    throw new RuntimeException("Book not found");
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid database type");
        }
        return Book.toEntity(book);
    }

    @Override
    public void deleteBook(String bookId, String dbType) {
        switch (dbType.toUpperCase()) {
            case "POSTGRES":
                Long id = Long.parseLong(bookId);
                bookPostgresRepository.deleteById(id);
                break;
            case "MONGO":
                bookMongoRepository.deleteById(bookId);
                break;
            default:
                throw new IllegalArgumentException("Invalid database type");
        }
    }

    @Override
    public Optional<BookDto> getBookById(String bookId, String dbType) {
        Optional<Book> book;
        switch (dbType.toUpperCase()) {
            case "POSTGRES":
                Long id = Long.parseLong(bookId);
                book = bookPostgresRepository.findById(id);
                break;
            case "MONGO":
                book = bookMongoRepository.findById(bookId);
                break;
            default:
                throw new IllegalArgumentException("Invalid database type");
        }
        return book.map(Book::toEntity);
    }

    @Override
    public List<BookDto> getAllBooks(String dbType) {
        List<Book> books;
        switch (dbType.toUpperCase()) {
            case "POSTGRES":
                books = bookPostgresRepository.findAll();
                break;
            case "MONGO":
                books = bookMongoRepository.findAll();
                break;
            default:
                throw new IllegalArgumentException("Invalid database type");
        }
        return books.stream().map(Book::toEntity).collect(Collectors.toList());
    }
}

