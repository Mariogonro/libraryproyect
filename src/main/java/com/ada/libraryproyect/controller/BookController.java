package com.ada.libraryproyect.controller;

import com.ada.libraryproyect.repository.entity.Book;
import com.ada.libraryproyect.repository.entity.User;
import com.ada.libraryproyect.service.impl.BookService;
import com.ada.libraryproyect.service.impl.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllUsers() {
        return bookService.getAll();
    }

    @GetMapping
    public Optional<Book> findById(String id) {
        return bookService.findById(id);
    }
    @PostMapping
    public ResponseEntity<Book> createUser(@RequestBody Book book) {
        bookService.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @DeleteMapping
    public void deleteById(String id) {
        bookService.deleteById(id);
    }
}
